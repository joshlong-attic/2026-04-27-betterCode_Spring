package com.example.adoptions.assistant;

import org.springaicommunity.agent.tools.SkillsTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
class AssistantController {

    private final ChatClient ai;

    AssistantController(ChatClient.Builder ai) {
        var skillTool = SkillsTool
                .builder()
                .addSkillsResource(new ClassPathResource("/META-INF/skills"))
                .build() ;
        this.ai = ai
                .defaultToolCallbacks(skillTool)
                .build();
    }

    @GetMapping("/ask")
    String ask(
//            @RequestParam String question
    ) {
        return this.ai
                .prompt()
                .user("should i adopt a dog or a cat?")
                .call()
                .content();
    }
}
