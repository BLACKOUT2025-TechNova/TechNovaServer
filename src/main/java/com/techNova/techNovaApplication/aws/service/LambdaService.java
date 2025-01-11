package com.techNova.techNovaApplication.aws.service;

import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvokeRequest;
import org.springframework.stereotype.Service;

@Service
public class LambdaService {

    private final AWSLambda awsLambda;

    public LambdaService() {
        this.awsLambda = AWSLambdaClientBuilder.standard()
                .withRegion("us-east-1")
                .build();
    }

    public String invokeLambda(String functionName, String payload) {
        InvokeRequest invokeRequest = new InvokeRequest()
                .withFunctionName(functionName)
                .withPayload(payload);
        return awsLambda.invoke(invokeRequest).getPayload().toString();
    }
}
