package com.fastcampus.customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller//1. 프로그램 등록
public class LoginController {
	private CustomerRepository customerRepo = new CustomerRepository();
	private String reID;
	@GetMapping("/") //spring 4.3 부터 사용가능
//	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String loginForm() {
		return "loginForm";
	}

	//2. 요청과 연결
	@PostMapping("/") //로그인
//	@RequestMapping(value = "/login",method=RequestMethod.POST)
	public String login(@RequestParam("id") String id, @RequestParam("pw")String pw, boolean rememberID, Model model, HttpSession session,HttpServletResponse response)throws IOException {
		System.out.println("id : " + id + " | pwd : "+ pw);
		System.out.println("rememberID : " + rememberID);
		if(id == null || pw == null || id.isEmpty() || pw.isEmpty()){ //아이디와 비밀번호 빈지 확인
			model.addAttribute("comment","아이디 또는 비밀번호를 입력해주세요. ");
			return "loginForm";
		}
		Customer customer = customerRepo.getCustomer(id); 
		if (customer == null) {//저장소에 있는 정보 불러와서 비교
			model.addAttribute("comment", "아이디 또는 비밀번호가 일치하지 않습니다."); //
			return "loginForm";
		}
		String idMatch = customer.getId();//저장소의 아이디와 비밀번호 불러와서 저장
		String pwMatch = customer.getPwd();

		if(id.equals(idMatch) && pw.equals(pwMatch)){//비교해서 맞으면 로그인
			model.addAttribute("id", id);//아이디 출력
			if(rememberID){ //ID저장이 true이면 쿠키가 저장
				Cookie cookie = new Cookie("id",id); //쿠키저장
				cookie.setMaxAge(60*60*24);//쿠키시간
				response.addCookie(cookie);//요청에 쿠키 저장
				System.out.println(cookie.getValue()); //쿠키값
				reID = cookie.getValue();//쿠키값을 ID저장에 저장
			}
			session.setAttribute("id", id);//session에 id저장
			System.out.println(session.getId());
			return "welcome";
		}else{
			model.addAttribute("comment", "아이디 또는 비밀번호가 일치하지 않습니다.");//비교했는데 틀린경우
			return "loginForm";
		}
	}

	@GetMapping("/join")
//	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String joinForm() {//회원가입 페이지
		return "joinForm";
	}

	@PostMapping("/join")//회원가입에서 보내는 페이지
//	@RequestMapping(value = "/join",method=RequestMethod.POST)
	public String join(Model model,@RequestParam("id") String id,@RequestParam("pw") String pw, String pw2,@RequestParam String phone,@RequestParam String name,String address, String sex, String email, String job)throws IOException {

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
		System.out.println("Customer : " + customer.getId());

		if(pw.equals(pw2)){ //비밀번호 1차와 2차가 맞으면 로그인 성공 틀리면 일치하지않음 출력
			model.addAttribute("comment","회원가입완료");
			return "loginForm";
		}else{
			model.addAttribute("comment","비밀번호가 일치하지 않습니다.");
			return "joinForm";
		}
	}
	@GetMapping("/logout")
//	@RequestMapping(value = "/logout",method = RequestMethod.GET)
	public String logout(HttpSession session, Model model,HttpServletResponse response){
		model.addAttribute("reid", reID); //logout 할때 ID저장값 출력
		session.invalidate();//logout시 session아웃
		Cookie cookie = new Cookie("id", reID); // 삭제할 쿠키와 같은 이름 쿠키 생성
		cookie.setMaxAge(0);                  // 유효기간을 0으로 설정(삭제)
		response.addCookie(cookie);//쿠키없애기
		reID=null;//끝나고 이미 ID저장값을 출력했으니깐 다시 null로
		return "loginForm";
	}

	@GetMapping("/info")//회원정보확인
//	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String info(Model model, HttpSession session)throws IOException {
		String id = session.getAttribute("id").toString();//session값으로 id저장
		Customer customer = customerRepo.getCustomer(id);//session값에서 가지고 온 id를 저장소에서 가지고와서 customer에 저장

		if (customer != null) {//customer가 null이 아니면 출력
			model.addAttribute("id", customer.getId());//불러온 id값
			model.addAttribute("name", customer.getName());//불러온 name값
			model.addAttribute("phone", customer.getPhone());//불러온phone값
			model.addAttribute("address", customer.getAddress());//불러온 address값
			model.addAttribute("sex", customer.getSex());//불러온 성별값
			model.addAttribute("email", customer.getEmail());//불러온 email값
			model.addAttribute("job", customer.getJob());//불러온 job값
			model.addAttribute("date",customer.getDate());//불러온 회원가입시간값
			return "infoForm";//infoForm에 출력
		} else {
			model.addAttribute("comment", "해당 아이디의 고객을 찾을 수 없습니다.");
			return "loginForm";//고객이 null이면 로그인폼으로 다시 돌아가기
		}
	}
	@GetMapping("/welcome")
//	@RequestMapping(value = "/welcome",method = RequestMethod.GET)
	public String welcome(Model model, HttpSession session){
		String id = session.getAttribute("id").toString(); //session에 저장된 id값 저장
		if(id.isEmpty() || id == null){//id가 비거나  null이면 loginform으로
			return "loginForm";
		}
		model.addAttribute("id",id);//아니면 welcome에서 id출력
		return "welcome";
	}
}
