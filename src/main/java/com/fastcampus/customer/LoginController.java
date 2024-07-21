package com.fastcampus.customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller//1. 프로그램 등록
public class LoginController {
	private CustomerRepository customerRepo = new CustomerRepository();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String loginForm() {
		return "loginForm";
	}

	//2. 요청과 연결
	@RequestMapping(value = "/login",method=RequestMethod.POST)
	public String login(@RequestParam("id") String id, @RequestParam("pw")String pw, Model model)throws IOException {
		System.out.println("id : " + id + "pwd : "+ pw);
		if(id == null || pw == null || id.isEmpty() || pw.isEmpty()){
			model.addAttribute("comment","아이디 또는 비밀번호를 입력해주세요. ");
			return "loginForm";
		}
		Customer customer = customerRepo.getCustomer(id);
		if (customer == null) {
			model.addAttribute("comment", "아이디 또는 비밀번호가 일치하지 않습니다.");
			return "loginForm";
		}
		String idMatch = customer.getId();
		String pwMatch = customer.getPwd();

		if(id.equals(idMatch) && pw.equals(pwMatch)){
			model.addAttribute("id", id);
			return "welcome";
		}else{
			model.addAttribute("comment", "아이디 또는 비밀번호가 일치하지 않습니다.");
			return "loginForm";
		}
	}

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String joinForm() {
		return "joinForm";
	}

	@RequestMapping(value = "/join",method=RequestMethod.POST)
	public String join(Model model,@RequestParam("id") String id,@RequestParam("pw") String pw, String pw2,String name,String address, String sex, String email, String job)throws IOException {

		if (id == null || pw == null || name == null || id.isEmpty() || pw.isEmpty() || name.isEmpty()) {
			model.addAttribute("comment", "아이디와 비밀번호 그리고 이름까지 입력해주세요.");
			return "joinForm";
		}
		if(customerRepo.idCheck(id)){
			model.addAttribute("comment", "아이디가 이미 존재합니다.");
			return "joinForm";
		}


		Customer customer = new Customer(id,pw,name,address,sex,email,job);

		customerRepo.addCustomer(customer);
		System.out.println("Customer : " + customer.getId());


		if(pw.equals(pw2)){
			model.addAttribute("comment","회원가입완료");
			return "loginForm";
		}else{
			model.addAttribute("comment","비밀번호가 일치하지 않습니다.");
			return "joinForm";
		}
	}
	@RequestMapping(value = "/logout",method = RequestMethod.GET)
	public String logout(){
		return "loginForm";
	}

	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String info(@RequestParam("id") String id, Model model){

		Customer customer = customerRepo.getCustomer(id);
		if (customer != null) {
			model.addAttribute("id", customer.getId());
			model.addAttribute("name", customer.getName());
			model.addAttribute("address", customer.getAddress());
			model.addAttribute("sex", customer.getSex());
			model.addAttribute("email", customer.getEmail());
			model.addAttribute("job", customer.getJob());
			model.addAttribute("date",customer.getDate());
			return "infoForm";
		} else {
			model.addAttribute("comment", "해당 아이디의 고객을 찾을 수 없습니다.");
			return "loginForm";
		}
	}
	@RequestMapping(value = "/welcome",method = RequestMethod.GET)
	public String welcome(@RequestParam("id") String id, Model model){
		model.addAttribute("id",id);
		return "welcome";
	}
}
