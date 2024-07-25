package com.fastcampus.customer;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller//1. 프로그램 등록
public class LoginController {
	private CustomerRepository customerRepo = new CustomerRepository();


	//2. 요청과 연결
	@PostMapping("/login") //로그인
	public String login(@RequestParam("id") String id, @RequestParam("pw")String pw, boolean rememberID,
						Model model, HttpSession session,HttpServletResponse response)throws IOException {
		if(id == null || pw == null || id.isEmpty() || pw.isEmpty()){ //아이디와 비밀번호 빈지 확인
			return "redirect:/";
		}
		if (!(loginCheck(id,pw))) {//저장소에 있는 정보 불러와서 비교
			String msg = URLEncoder.encode("id 또는 pwd가 일치하지 않습니다.", "utf-8");
			return "redirect:/?msg="+msg;
		}
		if(rememberID){ //ID저장이 true이면 쿠키가 저장
			Cookie cookie = new Cookie("idcheck",id); //쿠키저장
//				cookie.setMaxAge(60*60*24);//쿠키시간
			response.addCookie(cookie);//요청에 쿠키 저장
		}else{
			Cookie cookie = new Cookie("idcheck", session.getId()); // 삭제할 쿠키와 같은 이름 쿠키 생성
			cookie.setMaxAge(0);                  // 유효기간을 0으로 설정(삭제)
			response.addCookie(cookie);//쿠키없애기
		}
		session.setAttribute("id", id);//session에 id저장
		Cookie cookie = new Cookie("id",id); //쿠키저장
//			cookie.setMaxAge(60*60*24);//쿠키시간
		response.addCookie(cookie);//요청에 쿠키 저장
		model.addAttribute("id", id);//아이디 출력
		return "welcome";
	}


	@GetMapping("/join")
	public String joinForm() {//회원가입 페이지
		return "joinForm";
	}

	@PostMapping("/join")//회원가입에서 보내는 페이지
	public String join(Model model,@RequestParam String id,@RequestParam String pw, String pw2,@RequestParam String phone,@RequestParam String name,String address, String sex, String email, String job)throws IOException {
		//id, pw ,phone, name은 필수로 적어야 하므로 RequestParam
		if (id == null || pw == null || name == null || phone == null || id.isEmpty() || pw.isEmpty() || name.isEmpty() || phone.isEmpty()) {
			model.addAttribute("comment", "아이디, 비밀번호, 이름, 번호까지 꼭 입력해주세요.");
			return "joinForm";//id, pw, name, phone 이 null이거나 비어있으면 comment출력
		}
		if(customerRepo.idCheck(id)){ //저장소에 id가 있으면 출력
			model.addAttribute("comment", "아이디가 이미 존재합니다.");
			return "joinForm";
		}
		//customer 객체 생성후 저장소에 저장
		Customer customer = new Customer( id, pw, name, phone, address, sex, email, job);
		customerRepo.addCustomer(customer);

		if(pw.equals(pw2)){ //비밀번호 1차와 2차가 맞으면 로그인 성공 틀리면 일치하지않음 출력
			model.addAttribute("comment","회원가입완료");
			return "loginForm";
		}else{
			model.addAttribute ("comment","비밀번호가 일치하지 않습니다.");
			return "joinForm";
		}
	}
	@GetMapping("/logout")
	public String logout(HttpSession session, Model model,HttpServletResponse response){

		session.invalidate();//logout시 session아웃
		Cookie cookie = new Cookie("id", session.getId()); // 삭제할 쿠키와 같은 이름 쿠키 생성
		cookie.setMaxAge(0);                  // 유효기간을 0으로 설정(삭제)
		response.addCookie(cookie);//쿠키없애기
		return "loginForm";
	}

	@GetMapping("/info")//회원정보확인
	public String info(Model model, HttpSession session)throws IOException {
		if(session.getAttribute("id")!=null){
			customerinfo(session.getAttribute("id").toString());
			return "infoForm";
		}else {
			model.addAttribute("comment", "해당 아이디의 고객을 찾을 수 없습니다.");
			return "loginForm";//고객이 null이면 로그인폼으로 다시 돌아가기
		}
	}
	@GetMapping("/welcome")
	public String welcome(Model model, HttpSession session){
		String id = session.getAttribute("id").toString(); //session에 저장된 id값 저장
		if(id.isEmpty() || id == null){//id가 비거나  null이면 loginform으로
			return "loginForm";
		}
		model.addAttribute("id",id);//아니면 welcome에서 id출력
		return "welcome";
	}
//	@GetMapping("/updateCustomer")
//	public String updateCustomer(){
//		return "updateCustomer";
//	}
	private @ModelAttribute("customer") Customer customerinfo(String id){
		Customer customer = customerRepo.getCustomer(id);//session값에서 가지고 온 id를 저장소에서 가지고와서 customer에 저장
		return customer;
	}
	private boolean loginCheck(String id, String pw){ //id와 pw가 일치해야 작동
		Customer customer = customerRepo.getCustomer(id); //저장된 아이디가 없다?
		return (customer.getId().equals(id) && customer.getPwd().equals(pw));
	}
}
