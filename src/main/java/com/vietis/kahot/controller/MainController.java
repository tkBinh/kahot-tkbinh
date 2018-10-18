package com.vietis.kahot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.vietis.kahot.service.GameService;
import com.vietis.kahot.service.QuestionSetService;

@Controller
public class MainController {
	
	@Autowired
	private QuestionSetService questionSetService;
	
	@Autowired
	private GameService gameService;
	
	@RequestMapping(value = "/public-quiz")
	private String getAllPublicQuiz(Model model) {
		model.addAttribute("public_quiz", questionSetService.findPublicQuiz());
		return "public-quiz";
	}
	
	@RequestMapping(value = "/my-quiz")
	private String getAllUserQuiz(Model model) {
		model.addAttribute("my_quiz",questionSetService.findMyQuiz());
		return "my-quiz";
	}
	
	@RequestMapping(value = "/player-pin")
	public String pinPage() {
		return "player/player-pin";
	}
	
	@RequestMapping(value = "/player-nick-name")
	public String playerPage(@RequestParam("pin") Integer pin, Model model) {
		System.out.println("Lấy được pin nhé "+pin);
		model.addAttribute("gameId", gameService.getGameIdByPin(pin));
		return "player/player-nick-name";
	}
	
	@RequestMapping(value = "/check-pin")
	public String checkPin(@RequestParam("pin") Integer pin, Model model) {
		
		if (pin.equals(null)) {	
			model.addAttribute("errorPinAtt", "Please input Pin to join game").toString();
			return "player/player-pin";
		}
		
//		try {
//			Integer.parseInt(pin);
//		} catch (NumberFormatException ex) {
//			ex.printStackTrace();
//			model.addAttribute("errorPinAtt", "PIN is numeric, please input numeric character").toString();
//			return "player/player-pin";
//		}
		
		for (int pinGame : gameService.getPinGameOpenning()) {
			if (pin == pinGame) {
				System.out.print("PIN check OK");
				model.addAttribute("pin", pin);
				return "forward:player-nick-name";
			}
		}
		model.addAttribute("errorPinAtt","We didn't recognize that game PIN. Please check and try again.").toString();
		return "player/player-pin";
	}
}
