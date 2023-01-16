# Adobe Document Generation API Sample

## Description
This is a "Hello World" level sample of [Adobe Document Generation API](https://developer.adobe.com/document-services/apis/doc-generation/). This sample generates pdf file from word template and json data.

The word template exist src/resources/document-template.docx. 
Corresponding json data is embeded in sample code (SimpleGenerationApiDemo.java).

## Requirement
API credential is required to run this sample. API credential is availabe from the above url.

## Build
~~~
gradle build
~~~

## Run
~~~
gradle run
~~~

After running the sample, output/mergeOut.pdf will be created as a result.
