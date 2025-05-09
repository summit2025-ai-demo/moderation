package org.parasol.customerservice.moderation;

import io.quarkus.runtime.annotations.StaticInitSafe;
import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;

import java.time.Duration;
import java.util.Optional;

@StaticInitSafe
@ConfigMapping(prefix = "langchain4j.openai.moderation")
public interface LangChain4jOpenAIModerationConfig {

    String baseUrl();

    String modelName();

    String apiKey();

    Optional<String> organizationId();

    Optional<String> projectId();

    @WithDefault("10s")
    Duration timeout();

    @WithDefault("1")
    Integer maxRetries();

    @WithDefault("false")
    Boolean logRequests();

    @WithDefault("false")
    Boolean logResponses();

}
