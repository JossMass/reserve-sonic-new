package pe.edu.upc.reservesonic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.reservesonic.model.entity.Studio;
import pe.edu.upc.reservesonic.service.crud.StudioService;

@Controller
@RequestMapping("/studios")
public class StudioController {
    @Autowired
    private StudioService studioService;

    @GetMapping
    public String list(Model model) {
        try {
            List<Studio> studios = studioService.getAll();
            model.addAttribute("studios", studios);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        return "studios/listStudios";
    }
    
    @PostMapping("save")
    public String saveEdit(Model model, @ModelAttribute("studioEdit") Studio studio) {
		try {
			Studio studioReturn = studioService.update(studio);
			model.addAttribute("studio", studioReturn);
			return "studios/view";
		} catch(Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "redirect:/studios";
	}
    
    @GetMapping("new")
	public String newStudio(Model model) {
		try {
			Studio studio = new Studio();
			model.addAttribute("roomNew", studio);
			return "studios/new";
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		
		return "redirect:/studios";
	}
    
	@PostMapping("savenew")
	public String saveNew(Model model, @ModelAttribute("studioNew") Studio studio) {
		try {
			Studio studioReturn = studioService.create(studio);
			model.addAttribute("studio", studioReturn);
			return "studios/view";
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		
		return "redirect:/studios";
	}
    
    
}