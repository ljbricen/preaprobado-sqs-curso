package com.example.creditospreaprobadossqs.services;

import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.example.creditospreaprobadossqs.model.Libranza;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class LibranzaSQSService {

    private final AmazonSQS sqsClient;

    private final Environment env;

    private String getQueueUrl(String queueName) {
        return sqsClient.getQueueUrl(queueName).getQueueUrl();
    }

    public String publishStandardQueueMessage(String queueName, Libranza libranza) {

        Map<String, MessageAttributeValue> atributosMensaje = new HashMap<>();
        atributosMensaje.put("prestamo",
                new MessageAttributeValue().withDataType("String").withStringValue(libranza.prestamo()));
        atributosMensaje.put("consumido",
                new MessageAttributeValue().withDataType("String").withStringValue(libranza.consumido()));
        atributosMensaje.put("documento",
                new MessageAttributeValue().withDataType("String").withStringValue(libranza.documento()));
        atributosMensaje.put("vencimiento",
                new MessageAttributeValue().withDataType("String").withStringValue(libranza.vencimiento()));

        SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(getQueueUrl(queueName))
                .withMessageBody(libranza.documento())
                .withDelaySeconds(Integer.parseInt(env.getProperty("aws.sqs.delay-seconds")))
                .withMessageAttributes(atributosMensaje);

        return sqsClient.sendMessage(sendMessageRequest).getMessageId();
    }

}
