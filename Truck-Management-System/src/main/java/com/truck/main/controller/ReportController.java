package com.truck.main.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truck.main.model.TruckDetails;
import com.truck.main.model.TruckDetailsReponce;
import com.truck.main.service.ReportService;

@RestController
@CrossOrigin("*")
public class ReportController {

	@Autowired
	private ReportService reportService;

	@GetMapping("/getReport")
	public List<TruckDetailsReponce> report() {

		List<TruckDetailsReponce> rr = new ArrayList<>();

		Map<String, List<TruckDetails>> data = reportService.generateReport();

		// using for-each loop for iteration over Map.entrySet()
		for (Map.Entry<String, List<TruckDetails>> entry : data.entrySet()) {

			TruckDetailsReponce item = new TruckDetailsReponce();

			item.setRouteName(entry.getKey());
			item.setTruckDetails(entry.getValue());
			rr.add(item);

			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());

		}

		return rr;
	}
}
