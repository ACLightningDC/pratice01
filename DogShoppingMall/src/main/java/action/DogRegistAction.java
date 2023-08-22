package action;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.DogRegistService;
import vo.ActionForward;
import vo.Dog;

public class DogRegistAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		ServletContext context = request.getServletContext();
		String uploadPath = context.getRealPath("/images");
		System.out.println("서버상의 실제경로(절대경로) : " + uploadPath);//콘솔에 출력
		
		File dir = new File(uploadPath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		int size = 1024*1024*10; 
		
		MultipartRequest multi = new MultipartRequest(request, uploadPath 
				,size ,"utf-8" 
				,new DefaultFileRenamePolicy());
		//[방법-1]-----------------------------------------------------------------
//		Dog dog = new Dog();//기본값으로 채워진
//		
//		//MultipartRequest 객체 안의 값으로 채움(이 객체안에 form에서 넘어온 정보가 들어가 있으므로)
//		//이때 , id 생략(이유?dog_seq.nextval로 자동 1증가하므로)            
//		dog.setKind(multi.getParameter("kind"));
//		dog.setCountry(multi.getParameter("country"));
//		dog.setPrice(Integer.parseInt(multi.getParameter("price")));
//		dog.setHeight(Integer.parseInt(multi.getParameter("height")));
//		dog.setWeight(Integer.parseInt(multi.getParameter("weight")));
//		
//		dog.setContent(multi.getParameter("content"));
//		dog.setImage(multi.getParameter("Image"));
		//read count 생략 -> 기본값 0 
		String image = multi.getFilesystemName("image");
		//[방법-2]-----------------------------------------------------------------
		Dog dog = new Dog(
				0, //0인 이유 ? insert할 때 id 값은 dog_seq.nextval로 자동 1증가
				multi.getParameter("kind"),
				multi.getParameter("country"),
				Integer.parseInt(multi.getParameter("price")),
				Integer.parseInt(multi.getParameter("height")),
				Integer.parseInt(multi.getParameter("weight")),
				multi.getParameter("content"),
				image,
				0//조회수 : 0부터 시작
				);
		/*---------------------------------------------------------------*/
		System.out.println(dog.toString());
		DogRegistService dogRegistService =  new DogRegistService();
		boolean isRegistSucess = dogRegistService.registDog(dog);//insert
		
		if(isRegistSucess) {
			forward = new ActionForward("dogList.dog", true);//리다이렉트 방식으로 개목록보기 요청
		}else {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('등록실패');");
			out.print("history.back();");
			out.print("</script>");
		}
		
		
		return forward;
	}

}
