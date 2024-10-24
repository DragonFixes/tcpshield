package net.tcpshield.tcpshield.text;

import lombok.Getter;
import lombok.Setter;
import net.tcpshield.tcpshield.utils.LoggingUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
public class TextManager {
    @Getter @Setter
    private static ConcurrentSkipListSet<HexPolicy> hexPolicies = new ConcurrentSkipListSet<>();

    public static void registerHexPolicy(HexPolicy policy) {
        hexPolicies.add(policy);
        LoggingUtils.logInfo("Registered HexPolicy with starter '" + policy.getStarter() + "' and ender '" + policy.getEnder() + "'.");
    }

    public static void registerHexPolicy(String starter, String ender) {
        registerHexPolicy(new HexPolicy(starter, ender));
    }

    public static void unregisterHexPolicy(HexPolicy policy) {
        unregisterHexPolicy(policy.getIdentifiably());
    }

    public static void unregisterHexPolicy(String identifiably) {
        hexPolicies.removeIf(policy -> policy.getIdentifiably().equals(identifiably));
    }

    // Extracts hex codes based on registered policies
    public static Matcher extractHexCodes(String input, Collection<HexPolicy> policies) {
//        StringBuilder regexBuilder = new StringBuilder("(");
//        policies.forEach(policy -> {
//            if (regexBuilder.length() > 1) regexBuilder.append("|");
//
//            String starter = MatcherUtils.makeLiteral(policy.getStarter());
//            String ender = MatcherUtils.makeLiteral(policy.getEnder());
//            regexBuilder.append(starter).append("[a-fA-F0-9]{6}").append(ender);
//        });
//        regexBuilder.append(")");
//
//        // This regex groups the hex codes, before, and after parts of each match
//        String regex = "(" + "(.*)" + regexBuilder + "(.*)" + ")";
        String regex = "(&#[a-fA-F0-9]{6})(.*?)(?=&#[a-fA-F0-9]{6}|$)";

        Pattern pattern = Pattern.compile(regex);

        return pattern.matcher(input);
    }

    // Extracts JSON strings based on a starting marker
    public static List<String> extractJsonStrings(String input, String startMarker) {
        List<String> jsonStrings = new ArrayList<>();
        int index = 0;

        while ((index = input.indexOf(startMarker, index)) != -1) {
            int braceCount = 0;
            int i;

            for (i = index + startMarker.length(); i < input.length(); i++) {
                char c = input.charAt(i);

                if (c == '{') {
                    braceCount++;
                } else if (c == '}') {
                    braceCount--;

                    if (braceCount == 0) break;
                }
            }

            if (braceCount == 0) {
                jsonStrings.add(input.substring(index + startMarker.length(), i + 1));
            }

            index = i + 1;
        }

        return jsonStrings;
    }
}
