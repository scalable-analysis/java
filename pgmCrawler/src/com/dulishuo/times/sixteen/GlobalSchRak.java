package com.dulishuo.times.sixteen;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.dulishuo.util.CrawlerUtil;

public class GlobalSchRak {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		crawler();
	}

	private static void crawler() {
		// TODO Auto-generated method stub
		String url = "";
		String response = "";
		List<String> res = new ArrayList<String>();
		
		url = "https://www.timeshighereducation.com/sites/default/files/datatables_json/the_wur_datatables-panel_pane_1-569090de7660728b5d4e8e1619236cec.json";
				
		response = CrawlerUtil.httpsRequest(url);
			
		JSONObject json = JSONObject.fromObject(response);
		System.out.println(json.getJSONArray("data").toString());
		System.out.println("--------Exit___________");
	}

}
