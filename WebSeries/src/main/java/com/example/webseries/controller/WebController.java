package com.example.webseries.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.webseries.entity.Web;
import com.example.webseries.repository.WebRepo;

@Controller
public class WebController {
	
	@Autowired
	WebRepo repo;
	
	@RequestMapping("/addSeries")
	@ResponseBody
	public Optional<Web> addBook(@RequestParam int id,String name,String season,String episode,String rating)
	{
		Web web = new Web();
		web.setId(id);
		web.setName(name);
		web.setSeason(season);
		web.setEpisode(episode);
		web.setRating(rating);
		repo.save(web);
		
		return repo.findById(id);
	}
	
	@RequestMapping("/series")
	@ResponseBody
	public List<Web> allSeries()
	{
		return repo.findAll();
	}
	
	@GetMapping("/series/id/{id}")
	@ResponseBody
	public Optional<Web> getSeriesId(@PathVariable int id)
	{
		return repo.findById(id);
	}
	
	@GetMapping("/series/name/{name}")
	public ResponseEntity<Optional<Web>> getSeriesName(@PathVariable String name)
	{
		return new ResponseEntity<Optional<Web>>(repo.findBynameLike("%"+name+"%"),HttpStatus.OK);
	}

	@RequestMapping("/update/{name}/{id}")
	@ResponseBody
	public Optional<Web> updateSeries(@PathVariable String name, @PathVariable int id)
	{
		repo.updateSeriesName(name, id);
		return repo.findById(id);
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public Optional<Web> deleteSeries(@PathVariable int id)
	{
		repo.deleteById(id);
		return repo.findById(id);
	}
}
