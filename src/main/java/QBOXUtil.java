import com.qbox.uploader.BulkUpload;

public class QBOXUtil {

	
	public static void main(String[] args) {
	    String JSON_FILE	=	"D:\\knowledgebase\\myproject\\twilio\\documents\\cloudsearch\\DataSet\\JSON\\JSONData.json";
	    

		JSONFileCreator creatror	=	new JSONFileCreator();
		BulkUpload upload	=	new BulkUpload();
		int option	=	1;
		try {
			switch (option) {
			case 1:
				creatror.generateFile(JSON_FILE, null);
				break;
			case 2:
				upload.bulkupload(JSON_FILE);
				break;

			default:
				break;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
