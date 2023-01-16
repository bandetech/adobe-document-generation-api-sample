package com.adobe.demo;

import java.io.IOException;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.pdfservices.operation.ExecutionContext;
import com.adobe.pdfservices.operation.auth.Credentials;
import com.adobe.pdfservices.operation.exception.ServiceApiException;
import com.adobe.pdfservices.operation.io.FileRef;
import com.adobe.pdfservices.operation.pdfops.options.documentmerge.DocumentMergeOptions;
import com.adobe.pdfservices.operation.pdfops.DocumentMergeOperation;
import com.adobe.pdfservices.operation.pdfops.options.documentmerge.OutputFormat;


public class SimpleGenerationApiDemo 
{
    private static final Logger logger = LoggerFactory.getLogger(SimpleGenerationApiDemo.class);
    
    public static void main( String[] args )
    {
        // 差し込みデータの準備
        final JSONObject jsonData = new JSONObject("{\"name\": \"山田　太郎\",\"greeting\": \"こんにちは！\"}");
        logger.info("Insert data :{}", jsonData);
        
        try {
            // API Credentialの読み込みとインスタンス化
            Credentials credentials = Credentials.serviceAccountCredentialsBuilder()
                                        .fromFile("pdfservices-api-credentials.json").build();

            // ExecutionContextの作成
            ExecutionContext executionContext = ExecutionContext.create(credentials);
            
            // DocumentMergeOptionsの作成
            DocumentMergeOptions mergeOptions = new DocumentMergeOptions(jsonData, OutputFormat.PDF);
            
            // DocumentMergeOperationの作成。
            DocumentMergeOperation operation = DocumentMergeOperation.createNew(mergeOptions);

            // テンプレートの読み込み
            FileRef template = FileRef.createFromLocalFile("src/main/resources/document-template.docx");
            operation.setInput(template);

            // テンプレートと差し込みデータからPDFを生成
            FileRef result = operation.execute(executionContext);

            // ファイルとして保存
            result.saveAs("output/mergeOut.pdf");

        } catch (IOException|ServiceApiException ex) {
            ex.printStackTrace();
        }

    }
}
