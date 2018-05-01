package edu.eden.newonetomany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class Main {

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    SongRepository songRepository;


    @RequestMapping("/")
    public String showIndex(Model model){

    model.addAttribute("allartists",artistRepository.findAll());
    return"index";
    }


    @RequestMapping("/addartists")
    public @ResponseBody String addArtists(Model model) {


        Artist a = new Artist("Michael Joseph Jackson");
        artistRepository.save(a);

        a = new Artist("Prince Rogers Nelson");
        artistRepository.save(a);

        a = new Artist("Gordon Matthew Thomas Sumner");

        a.setStagename("Sting");
        artistRepository.save(a);

        a = new Artist("Adele Laurie Blue Adkins");
        artistRepository.save(a);

        a = new Artist("Robyn Rihanna Fenty");
        artistRepository.save(a);
        return artistRepository.findAll().toString();

    }

@RequestMapping("/addsong")
    public String addSong(Model model){

        model.addAttribute("the Song",new Song());
        return "addsong";
}
@RequestMapping("/savesong")
    public String savaSong(@ModelAttribute("theSong") Song song, BindingResult result){
        songRepository.save(song);
        return"redirect:/";

}
@RequestMapping("/addsongwithform")
    public String addSongWithDropDown(Model model){
        model.addAttribute("theSong",new Song());
        model.addAttribute("allartists",artistRepository.findAll());
        return "addsongform";
}


}
