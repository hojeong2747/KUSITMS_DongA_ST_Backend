package teamE.dashboard;

//import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import teamE.dashboard.config.aws.AwsS3Service;

//@Slf4j
@SpringBootTest
class DashboardApplicationTests {

//	@Autowired
//	private S3UploadService s3UploadService;
//
//	@DisplayName("저장된 이미지(볼펜) 찾기")
//	@Test
//	public void findImg() {
//		String imgPath = s3UploadService.getThumbnailPath("pl.png");
//		log.info(imgPath);
//		Assertions.assertThat(imgPath).isNotNull();
//	}

    @Autowired
    private AwsS3Service s3UploadService;

    @DisplayName("저장된 이미지(볼펜) 찾기")
    @Test
    public void findImg() {
        String imgPath = s3UploadService.getThumbnailPath("pl.png");
//        log.info(imgPath);
        Assertions.assertThat(imgPath).isNotNull();
    }

}
