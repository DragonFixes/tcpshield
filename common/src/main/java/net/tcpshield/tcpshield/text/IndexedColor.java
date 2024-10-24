package net.tcpshield.tcpshield.text;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.network.chat.TextColor;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
public class IndexedColor implements Comparable<IndexedColor> {
    private int startOfThis;
    private int endOfThis;
    private String hex;
    private String after;
    private MatchResult matcher;
    private Color hexColor;
    private String hexCode;
    private TextColor foundColor;

    public IndexedColor(int startOfThis, int endOfThis, String hex, String after, MatchResult matcher) {
        this.startOfThis = startOfThis;
        this.endOfThis = endOfThis;
        this.hex = hex;
        this.after = after;
        this.matcher = matcher;
        this.hexCode = extractHexCode();
        this.hexColor = extractColor();
        this.foundColor = determineColor();
    }

    // Extracts the hexadecimal color value from the matched string
    private String extractHexCode() {
        String regex = "#?([A-Fa-f0-9]{6})"; // Match a 6-character hex color
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(getHex());

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "FFFFFF"; // Default to white if no color is found
        }
    }

    // Extracts the Color object from the hex value
    private Color extractColor() {
        if (getHexCode() == null) {
            hexCode = extractHexCode();
        }

        int r = Integer.parseInt(getHexCode().substring(0, 2), 16);
        int g = Integer.parseInt(getHexCode().substring(2, 4), 16);
        int b = Integer.parseInt(getHexCode().substring(4, 6), 16);

        return new Color(r, g, b);
    }

    // Determines the TextColor from the hex value, with error handling
    private TextColor determineColor() {
        if (getHexColor() == null) {
            hexColor = extractColor();
        }

        try {
            return TextColor.fromRgb(getHexColor().getRGB());
        } catch (NumberFormatException e) {
            // Log the error and use a fallback color
            System.err.println("Error parsing color: " + hexColor + " - " + e.getMessage());
            return TextColor.fromRgb(0xFFFFFF); // Default to white on error
        }
    }

    @Override
    public int compareTo(@NotNull IndexedColor o) {
        return Integer.compare(startOfThis, o.startOfThis);
    }
}
