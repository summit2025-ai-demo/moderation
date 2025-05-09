package org.parasol.customerservice.moderation;

import dev.langchain4j.model.moderation.Moderation;
import dev.langchain4j.model.moderation.ModerationModel;
import dev.langchain4j.model.openai.OpenAiModerationModel;
import dev.langchain4j.model.output.Response;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class MessageProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageProcessor.class);

    @Inject
    LangChain4jOpenAIModerationConfig config;

    ModerationModel model;

    void onStart(@Observes StartupEvent e) {
        model = initModerationModel();
    }

    public Boolean process(String contents) {
        Response<Moderation> response = model.moderate(contents);
        return response.content().flagged();
    }

    private ModerationModel initModerationModel() {
        LOGGER.info("Initializing moderation model");
        return OpenAiModerationModel.builder()
                .apiKey(config.apiKey())
                .modelName(config.modelName())
                .baseUrl(config.baseUrl())
                .maxRetries(config.maxRetries())
                .timeout(config.timeout())
                .logRequests(config.logRequests())
                .logResponses(config.logResponses())
                .build();
    }
}
