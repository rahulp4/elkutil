package com.qbox.uploader;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class BulkUpload {

    public static void main(String[] args) {
        long t = System.currentTimeMillis();

        URL url = null;
        String urlStr	=	"https://3a7721e6.qb0x.com:30002/ymm/_bulk/";
//        try {
//        	
//            url = new URL(System.getenv("COMPOSE_ELASTICSEARCH_URL"));
//        } catch (MalformedURLException me) {
//            System.err.println("COMPOSE_ELASTICSEARCH_URL not present or malformed");
//            System.exit(1);
//        }
//
//        String host = url.getHost();
//        int port = url.getPort();
//        String user = url.getUserInfo().split(":")[0];
//        String password = url.getUserInfo().split(":")[1];
//        String protocol = url.getProtocol();

        String userName	= "1fd0748f43709cb08803";
        String pass	=	"e4fd5698e6";
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(userName, pass));

       ;
        
        RestHighLevelClient client = new RestHighLevelClient(
            RestClient.builder(
               new HttpHost("3a7721e6.qb0x.com",30002,"https"))
                 .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider)));

        try {
            String indexName="ymm";
            Response response = client.getLowLevelClient().performRequest("HEAD", "/" + indexName);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 404) {
                CreateIndexRequest cireq=new CreateIndexRequest(indexName);
                CreateIndexResponse ciresp=client.indices().create(cireq);
                System.out.println("Created index");
            } else {
                System.out.println("Index exists");
            }

            BulkRequest request=new BulkRequest();

            int count=0;
            int batch = 1000;

            String JSON_FILE	=	"D:\\knowledgebase\\myproject\\twilio\\documents\\cloudsearch\\DataSet\\JSON\\JSONData.json";

            String filePathName = JSON_FILE;//"D:\\knowledgebase\\myproject\\twilio\\documents\\cloudsearch\\DataSet\\UploadToQBOX\\testbulk.json";
            
            BufferedReader br=new BufferedReader(new FileReader(filePathName));

            String line;

            while((line=br.readLine())!=null) {
                request.add(new IndexRequest(indexName,"_doc").source(line, XContentType.JSON));
                count++;
                if(count%batch==0) {
                    BulkResponse bulkresp = client.bulk(request);
                    if (bulkresp.hasFailures()) {
                        for (BulkItemResponse bulkItemResponse : bulkresp) {
                            if (bulkItemResponse.isFailed()) {
                                BulkItemResponse.Failure failure = bulkItemResponse.getFailure();
                                System.out.println("Error " + failure.toString());
                            }
                        }
                    }
                    System.out.println("Uploaded " + count + " so far");
                    request=new BulkRequest();
                }
            }

            if (request.numberOfActions() > 0) {
                BulkResponse bulkresp = client.bulk(request);
                if (bulkresp.hasFailures()) {
                    for (BulkItemResponse bulkItemResponse : bulkresp) {
                        if (bulkItemResponse.isFailed()) {
                            BulkItemResponse.Failure failure = bulkItemResponse.getFailure();
                            System.out.println("Error " + failure.toString());
                        }
                    }
                }
            }

            System.out.println("Total uploaded: " + count);
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        long tn = System.currentTimeMillis();
        System.out.println("Took " + (tn - t) / 1000 + " seconds");
    }
    
    
    public static void bulkupload(String args1,String qboxRestURL,String p_indexName) {
        long t = System.currentTimeMillis();

        URL url = null;
        String urlStr	=	qboxRestURL;
//        try {
//        	
//            url = new URL(System.getenv("COMPOSE_ELASTICSEARCH_URL"));
//        } catch (MalformedURLException me) {
//            System.err.println("COMPOSE_ELASTICSEARCH_URL not present or malformed");
//            System.exit(1);
//        }
//
//        String host = url.getHost();
//        int port = url.getPort();
//        String user = url.getUserInfo().split(":")[0];
//        String password = url.getUserInfo().split(":")[1];
//        String protocol = url.getProtocol();

        String userName	= "1fd0748f43709cb08803";
        String pass	=	"e4fd5698e6";
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(userName, pass));

       ;
        
        RestHighLevelClient client = new RestHighLevelClient(
            RestClient.builder(
               new HttpHost("3a7721e6.qb0x.com",30002,"https"))
                 .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider)));

        try {
            String indexName=p_indexName;//"ymm";
            Response response = client.getLowLevelClient().performRequest("HEAD", "/" + indexName);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 404) {
                CreateIndexRequest cireq=new CreateIndexRequest(indexName);
                CreateIndexResponse ciresp=client.indices().create(cireq);
                System.out.println("Created index");
            } else {
                System.out.println("Index exists");
            }

            BulkRequest request=new BulkRequest();

            int count=0;
            int batch = 1000;

            String JSON_FILE	=	args1; //"D:\\knowledgebase\\myproject\\twilio\\documents\\cloudsearch\\DataSet\\JSON\\JSONData.json";

            String filePathName = JSON_FILE;//"D:\\knowledgebase\\myproject\\twilio\\documents\\cloudsearch\\DataSet\\UploadToQBOX\\testbulk.json";
            
            BufferedReader br=new BufferedReader(new FileReader(filePathName));

            String line;

            while((line=br.readLine())!=null) {
                //System.out.println(line);
            	request.add(new IndexRequest(indexName,"_doc").source(line, XContentType.JSON));
                count++;
                if(count%batch==0) {
                    BulkResponse bulkresp = client.bulk(request);
                    if (bulkresp.hasFailures()) {
                        for (BulkItemResponse bulkItemResponse : bulkresp) {
                            if (bulkItemResponse.isFailed()) {
                                BulkItemResponse.Failure failure = bulkItemResponse.getFailure();
                                System.out.println("Error " + failure.toString());
                            }
                        }
                    }
                    System.out.println("Uploaded " + count + " so far");
                    request=new BulkRequest();
                }
            }
			/*
			 * if (request.numberOfActions() > 0) { BulkResponse bulkresp =
			 * client.bulk(request); if (bulkresp.hasFailures()) { for (BulkItemResponse
			 * bulkItemResponse : bulkresp) { if (bulkItemResponse.isFailed()) {
			 * BulkItemResponse.Failure failure = bulkItemResponse.getFailure();
			 * System.out.println("Error " + failure.toString()); } } } }
			 * 
			 */            System.out.println("Total uploaded: " + count);
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        long tn = System.currentTimeMillis();
        System.out.println("Took " + (tn - t) / 1000 + " seconds");
    }
}
