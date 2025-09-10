package gg.drak.tcpshield.text;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.Optional;

@Getter @Setter
public class UsersReplacements implements Comparable<UsersReplacements> {
    private String identifier;

    private Cache<String, String> replacements;

    public UsersReplacements(String identifier, Cache<String, String> replacements) {
        this.identifier = identifier;
        this.replacements = replacements;
    }

    public UsersReplacements(String identifier) {
        this(identifier, CacheBuilder.newBuilder()
                .expireAfterWrite(Duration.ofMinutes(30))
                .build(CacheLoader.from((s) -> null))
        );
    }

    public void addReplacement(String key, String value) {
        if (hasReplacement(key)) {
            removeReplacement(key);
        }
        replacements.put(key, value);
    }

    public void removeReplacement(String key) {
        replacements.invalidate(key);
    }

    public Optional<String> getReplacement(String key) {
        return Optional.ofNullable(replacements.getIfPresent(key));
    }

    public boolean hasReplacement(String key) {
        return getReplacement(key).isPresent();
    }

    public String getReplacement(String key, String orElse) {
        return getReplacement(key).orElse(orElse);
    }

    @Override
    public int compareTo(@NotNull UsersReplacements o) {
        return identifier.compareTo(o.identifier);
    }
}
