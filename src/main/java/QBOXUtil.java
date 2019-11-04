import com.qbox.uploader.BulkUpload;

public class QBOXUtil {

	
	public static void main(String[] args) {
	    String JSON_FILE	=	"D:\\knowledgebase\\myproject\\DataSet\\JSON\\JSONDataNov4.json";
	    String SAMPLE_XLSX_FILE_PATH = "D:\\knowledgebase\\myproject\\DataSet\\UploadToQBOX\\MASTER_DATA_SEARCH_3_NOV.xlsx";
//	    String SAMPLE_XLSX_FILE_PATH = "D:\\knowledgebase\\myproject\\DataSet\\UploadToQBOX\\Sample.xlsx";
//	    String JSON_FILE	=	"D:\\knowledgebase\\myproject\\DataSet\\JSON\\Sampleq.json";
	    
	    String qboxReSTURL	=	"https://3a7721e6.qb0x.com:30002/ymmv2/_bulk/";
	    String p_indexName	=	"ymmv2";

	    JSONFileCreator creatror	=	new JSONFileCreator();
		BulkUpload upload	=	new BulkUpload();
		int option	=	2;
		try {
			switch (option) {
			case 1:
				creatror.generateFile(JSON_FILE, SAMPLE_XLSX_FILE_PATH);
				break;
			case 2:
				
				upload.bulkupload(JSON_FILE,qboxReSTURL,p_indexName);
				break;

			default:
				break;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
