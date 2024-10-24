package net.tcpshield.tcpshield.utils;

import net.tcpshield.tcpshield.text.IndexedColor;
import net.tcpshield.tcpshield.text.TextManager;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import tv.quaint.objects.AtomicString;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.regex.Matcher;

public class ColorUtils {
    // Converts legacy color codes like &e to Minecraft color codes (§e)
    public static String colorizeHard(String message) {
        return message.replace("&0", "§0")
                .replace("&1", "§1")
                .replace("&2", "§2")
                .replace("&3", "§3")
                .replace("&4", "§4")
                .replace("&5", "§5")
                .replace("&6", "§6")
                .replace("&7", "§7")
                .replace("&8", "§8")
                .replace("&9", "§9")
                .replace("&a", "§a")
                .replace("&b", "§b")
                .replace("&c", "§c")
                .replace("&d", "§d")
                .replace("&e", "§e")
                .replace("&f", "§f")
                .replace("&k", "§k")
                .replace("&l", "§l")
                .replace("&m", "§m")
                .replace("&n", "§n")
                .replace("&o", "§o")
                .replace("&r", "§r");
    }

    // Converts legacy color codes like &e to Minecraft hex color codes (&#FFFFFF)
    public static String colorizeHexed(String message) {
        return message.replace("&0", "&#000000")
                .replace("&1", "&#0000AA")
                .replace("&2", "&#00AA00")
                .replace("&3", "&#00AAAA")
                .replace("&4", "&#AA0000")
                .replace("&5", "&#AA00AA")
                .replace("&6", "&#FFAA00")
                .replace("&7", "&#AAAAAA")
                .replace("&8", "&#555555")
                .replace("&9", "&#5555FF")
                .replace("&a", "&#55FF55")
                .replace("&b", "&#55FFFF")
                .replace("&c", "&#FF5555")
                .replace("&d", "&#FF55FF")
                .replace("&e", "&#FFFF55")
                .replace("&f", "&#FFFFFF")
                .replace("&k", "§k")
                .replace("&l", "§l")
                .replace("&m", "§m")
                .replace("&n", "§n")
                .replace("&o", "§o")
                .replace("&r", "§r");
    }

    // Handles both legacy and hex color codes, converting them to a MutableComponent
    public static MutableComponent colorizeToComponent(String from) {
        String raw = colorizeHard(from); // Converts legacy codes like &e to Minecraft color codes (§e)
        MutableComponent component = Component.empty();
        List<MutableComponent> componentsList = colorizeHex(raw);

        for (Component c : componentsList) {
            component = component.append(c);
        }

        return component;
    }

    // Converts a string with hex and legacy color codes to a list of MutableComponents
    public static List<MutableComponent> colorizeHex(String from) {
        String raw = colorizeHard(from); // Converts legacy codes like &e to Minecraft color codes (§e)
        AtomicString runningString = new AtomicString(raw);
        List<MutableComponent> toReturn = new ArrayList<>();
        ConcurrentSkipListSet<IndexedColor> indexedColors = new ConcurrentSkipListSet<>();

        // Extract and create IndexedColor objects from the input string
        Matcher matcher = TextManager.extractHexCodes(runningString.get(), TextManager.getHexPolicies());

        int lastEnd = 0;

        while (matcher.find(lastEnd)) {
            String hex = matcher.group(1) == null ? "" : matcher.group(1);
            String after = matcher.group(2) == null ? "" : matcher.group(2);

            // Only add hex codes if they are valid
            if (hex != null && ! hex.isEmpty()) {
                IndexedColor indexedColor = new IndexedColor(matcher.start(), matcher.end(), hex, after, matcher.toMatchResult());
                indexedColors.add(indexedColor);
            }

//            lastEnd = matcher.end(1); // to ensure the next match starts correctly
            lastEnd = matcher.end(); // Update to start search after the end of the current match
        }

        String whole = runningString.get();
        int wholeIndex = 0;

        // Iterate over each indexed color found in the string
        for (IndexedColor color : indexedColors) {
            // Add the text before the current color segment as a separate component
            if (wholeIndex < color.getStartOfThis()) {
                String before = whole.substring(wholeIndex, color.getStartOfThis());
                toReturn.add(colorizeOneComponent(before));
            }

            // Create a style for the hex color or directly use Minecraft color codes
            MutableComponent component;
            if (color.getHex().contains("#")) {
                // Convert hex code to the Minecraft compatible format §x§R§R§G§G§B§B
                Style style = Style.EMPTY.withColor(color.getFoundColor());
                component = Component.literal(color.getAfter()).withStyle(style);
            } else {
                // Convert directly with the colorizeOneComponent method
                component = colorizeOneComponent(color.getAfter());
            }

            toReturn.add(component);
            wholeIndex = color.getEndOfThis();
        }

        // Append any remaining text after the last colored segment
        if (wholeIndex < whole.length()) {
            String remaining = whole.substring(wholeIndex);
            toReturn.add(colorizeOneComponent(remaining));
        }

        return toReturn;
    }

    // Converts a string to a single MutableComponent using legacy color codes
    public static MutableComponent colorizeOneComponent(String from) {
        String replaced = colorizeHard(from); // Converts & codes to Minecraft's § codes
        return Component.literal(replaced);
    }
}
