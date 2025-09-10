package gg.drak.tcpshield.text;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public class HexPolicy implements Comparable<HexPolicy> {
    private String starter;
    private String ender;

    public HexPolicy(String starter, String ender) {
        this.starter = starter;
        this.ender = ender;
    }

    public HexPolicy() {
        this("{#", "}");
    }

    // Returns a formatted string with the hex code within starter and ender
    public String getResult(String hex) {
        return starter + hex + ender;
    }

    // Provides an identifier string to uniquely identify this policy
    public String getIdentifiably() {
        return getWith("000000");
    }

    // Returns the starter and ender surrounding a given hex code
    public String getWith(String hexCode) {
        return starter + hexCode + ender;
    }

    public static String getHexRegex() {
        return "[a-fA-F0-9]{6}";
    }

    @Override
    public int compareTo(@NotNull HexPolicy o) {
        return getIdentifiably().compareTo(o.getIdentifiably());
    }
}
