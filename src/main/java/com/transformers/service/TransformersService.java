package com.transformers.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.ServletContext;
import com.transformers.context.TransformersContext;
import com.transformers.model.Transformers;

public class TransformersService {
	
	private ConcurrentHashMap<Integer, Transformers> transformersMap = null;
	private int battles = 0;
	private List<String> outputList = new ArrayList<String>();
	private Map<String, String> survivorMap = new HashMap<String, String>();
	private List<String> miniListAutoBot = new ArrayList<String>();
	private List<String> miniListDecepticon = new ArrayList<String>();
	private Iterator<String> objItrminiListAutoBot = null;
	private Iterator<Entry<String, String>> objItrSurvivorMap = null;
	private Iterator<String> objItrminiListDecepticon = null;
	
	public TransformersService(){
		ServletContext context = TransformersContext.getApplicationContext();
		if(context != null){
			transformersMap = (ConcurrentHashMap<Integer, Transformers>) context.getAttribute("transformersMap");
		}
		else{
			transformersMap = new ConcurrentHashMap<Integer, Transformers>();
			transformersMap.put(1, new Transformers(1,6,6,7,9,5,2,9,7,"Autobots","Bluestreak",false));
			transformersMap.put(2, new Transformers(2,8,9,2,6,7,5,6,10,"Decepticons","Soundwave",false));
			transformersMap.put(3, new Transformers(3,4,4,4,4,4,4,4,4,"Autobots","Hubcap",false));
		}
			
	}

	public Transformers addTransformers(Transformers transformer) {
		transformer.setTransformerId(transformersMap.size()+1);
		transformer.setOverallRating(transformer.getStrength() + transformer.getIntelligence() + transformer.getSpeed() + transformer.getEndurance() + transformer.getFirepower());
		transformersMap.put(transformer.getTransformerId(), transformer);
		return transformer;
	}

	public Transformers getTransformers(int id) {
		// TODO Auto-generated method stub
		return transformersMap.get(id);
	}

	public Transformers updateTransformers(Transformers transformer,int id) {
		if(transformer.getTransformerId() <= 0){
			return null;
		}
		transformersMap.put(transformer.getTransformerId(), transformer);
		return transformer;
	}

	public Transformers deleteTransformer(int id) {
		return transformersMap.remove(id);
	}

	public ArrayList<Transformers> getAllTransformers() {
		return new ArrayList<Transformers>(transformersMap.values());
	}

	public CopyOnWriteArrayList<Transformers> getAllAutobotsTransformers() {
		Set<Entry<Integer, Transformers>> objSet = transformersMap.entrySet();
		Iterator<Entry<Integer, Transformers>> objItr = objSet.iterator();
		Map<Integer, Transformers> objHashMap = new HashMap<Integer, Transformers>();
		while(objItr.hasNext()){
			Entry<Integer, Transformers> objEntry = objItr.next();
			if(objEntry.getValue().getType().equals("Autobots")){
			objHashMap.put(objEntry.getKey(), objEntry.getValue());
			}
		}
		return new CopyOnWriteArrayList<Transformers>(objHashMap.values());
	}
	
	public CopyOnWriteArrayList<Transformers> getAllDecepticonsTransformers() {
		Set<Entry<Integer, Transformers>> objSet = transformersMap.entrySet();
		Iterator<Entry<Integer, Transformers>> objItr = objSet.iterator();
		Map<Integer, Transformers> objHashMap = new HashMap<Integer, Transformers>();
		while(objItr.hasNext()){
			Entry<Integer, Transformers> objEntry = objItr.next();
			if(objEntry.getValue().getType().equals("Decepticons")){
			objHashMap.put(objEntry.getKey(), objEntry.getValue());
			}
		}
		return new CopyOnWriteArrayList<Transformers>(objHashMap.values());
	}

	public List<String> startFight() {
		Collections.sort(getAllTransformers());
		List<Transformers> autoBotListTransformers = getAllAutobotsTransformers();
		List<Transformers> decepticonsTransformers = getAllDecepticonsTransformers();
		 if(autoBotListTransformers.size() > decepticonsTransformers.size()){
			autoBotListTransformers = autoBotListTransformers.subList(0, autoBotListTransformers.size()-1);
			System.out.println("autoBotListTransformers size has become : "+autoBotListTransformers.size());
		}else if(decepticonsTransformers.size() > autoBotListTransformers.size()){
			decepticonsTransformers = decepticonsTransformers.subList(0, decepticonsTransformers.size()-1);
			System.out.println("decepticonsTransformers size has become : "+decepticonsTransformers.size());
		}
		Iterator<Transformers> itrAutobots = autoBotListTransformers.iterator();
		Iterator<Transformers> itrDecepticons = decepticonsTransformers.iterator();
		while(itrAutobots.hasNext() && itrDecepticons.hasNext()){
			Transformers autobots = itrAutobots.next();
			Transformers decepticons = itrDecepticons.next();
			if(!autobots.getName().equals("Optimus Prime") && !decepticons.getName().equals("Predaking")){
				if(autobots.getOverallRating() > decepticons.getOverallRating()){
					if((autobots.getCourage() > decepticons.getCourage() && autobots.getStrength() > decepticons.getStrength())){
						if((autobots.getCourage() - decepticons.getCourage()) > 4 && (autobots.getStrength() - decepticons.getStrength()) >= 3){
							autobots.setFightResult(true);
							deleteTransformer(decepticons.getTransformerId());
							System.out.println("Decepticon lost because of courage or strength :: "+decepticons.getName());
							battles++;
						}else if(autobots.getSkill() > decepticons.getSkill()){
							if((autobots.getSkill() - decepticons.getSkill()) >= 3){
								autobots.setFightResult(true);
								deleteTransformer(decepticons.getTransformerId());
								System.out.println("Decepticon lost because of skill :: "+decepticons.getName());
								battles++;
							}
						}
					}else if((autobots.getCourage() < decepticons.getCourage() && autobots.getStrength() < decepticons.getStrength())){
							if(decepticons.getCourage() - autobots.getCourage() > 4 && decepticons.getStrength() - autobots.getStrength() >= 3){
								decepticons.setFightResult(true);
								deleteTransformer(autobots.getTransformerId());
								System.out.println("Autobot lost because of courage or strength :: "+autobots.getName());
								battles++;
							}else if(decepticons.getSkill() > autobots.getSkill()){
								if((decepticons.getSkill() - autobots.getSkill()) >= 3){
									decepticons.setFightResult(true);
									deleteTransformer(autobots.getTransformerId());
									System.out.println("Autobot lost because of skill :: "+autobots.getName());
									battles++;
								}
							}
						}
					else{
							autobots.setFightResult(true);
							deleteTransformer(decepticons.getTransformerId());
							System.out.println("Decepticon lost because of overall rating:: "+decepticons.getName());
							battles++;
					}
				}else if(decepticons.getOverallRating() > autobots.getOverallRating()){
					if((autobots.getCourage() < decepticons.getCourage() && autobots.getStrength() < decepticons.getStrength())){
						if(decepticons.getCourage() - autobots.getCourage() > 4 && decepticons.getStrength() - autobots.getStrength() >= 3){
							decepticons.setFightResult(true);
							deleteTransformer(autobots.getTransformerId());
							System.out.println("Autobot lost because of courage or strength :: "+autobots.getName());
							battles++;
						}else if(decepticons.getSkill() > autobots.getSkill()){
							if((decepticons.getSkill() - autobots.getSkill()) >= 3){
								decepticons.setFightResult(true);
								deleteTransformer(autobots.getTransformerId());
								System.out.println("Autobot lost :: because of skill "+autobots.getName());
								battles++;
							}
						}
					}else if((autobots.getCourage() > decepticons.getCourage() && autobots.getStrength() > decepticons.getStrength())){
						if((autobots.getCourage() - decepticons.getCourage()) > 4 && (autobots.getStrength() - decepticons.getStrength()) >= 3){
							autobots.setFightResult(true);
							deleteTransformer(decepticons.getTransformerId());
							System.out.println("Decepticon lost because of courage or strength :: "+decepticons.getName());
							battles++;
						}else if(autobots.getSkill() > decepticons.getSkill()){
							if((autobots.getSkill() - decepticons.getSkill()) >= 3){
								autobots.setFightResult(true);
								deleteTransformer(decepticons.getTransformerId());
								System.out.println("Decepticon lost because of skill :: "+decepticons.getName());
								battles++;
							}
						}
					}else{
							decepticons.setFightResult(true);
							deleteTransformer(autobots.getTransformerId());
							System.out.println("Autobot lost because of overall rating :: "+autobots.getName());
							battles++;
					}
				}else{
					System.out.println("The transformers seems to have the same overall rating, so removing all!! ");
					transformersMap.remove(autoBotListTransformers);
					transformersMap.remove(decepticonsTransformers);
					transformersMap.values().removeAll(getAllTransformers());
					return new ArrayList<String>();
				}
			}else if(autobots.getName().equals("Optimus Prime") && decepticons.getName().equals("Predaking")){
				System.out.println("The transformers seems to be the same, so removing all competitors and also removing the transformers because its a tie!! ");
				transformersMap.remove(autoBotListTransformers);
				transformersMap.remove(decepticonsTransformers);
				transformersMap.values().removeAll(getAllTransformers());
				return new ArrayList<String>();
			}
			else if(decepticons.getName().equals("Predaking")){
				System.out.println("Predaking always wins!!!!! ");
				decepticons.setFightResult(true);
				deleteTransformer(autobots.getTransformerId());
				System.out.println("Autobot lost :: "+autobots.getName());
				battles++;
			}else if(autobots.getName().equals("Optimus Prime")){
				System.out.println("Optimus Prime always wins!!!!! ");
				autobots.setFightResult(true);
				deleteTransformer(decepticons.getTransformerId());
				System.out.println("Decepticon lost :: "+decepticons.getName());
				battles++;
			}
		}
		Set<Entry<Integer, Transformers>> objSet = (Set<Entry<Integer, Transformers>>) transformersMap.entrySet();
		Iterator<Entry<Integer, Transformers>> objItr = objSet.iterator();
		while(objItr.hasNext()){
			Entry<Integer, Transformers> entry = objItr.next();
				if(!entry.getValue().isFightResult()){
					survivorMap.put(entry.getValue().getType(), entry.getValue().getName());
				}
				if(entry.getValue().isFightResult()){
					
				if(entry.getValue().getType().equals("Autobots"))
					miniListAutoBot.add(entry.getValue().getName());
				else 
					miniListDecepticon.add(entry.getValue().getName());
				}
		}
		if(!miniListDecepticon.isEmpty()){
			objItrminiListDecepticon = miniListDecepticon.iterator();
		}
		if(!miniListAutoBot.isEmpty()){
			objItrminiListAutoBot = miniListAutoBot.iterator();
		}
		
		if(miniListAutoBot.size() > miniListDecepticon.size()){
			while(objItrminiListAutoBot.hasNext()){
				outputList.add("Winning Team : (Autobots) " + objItrminiListAutoBot.next());
			}
		}
		else if(miniListDecepticon.size() > miniListAutoBot.size()){
			while(objItrminiListDecepticon.hasNext()){
				outputList.add("Winning Team : (Decepticons) "+objItrminiListDecepticon.next());
			}
		}else{
			miniListAutoBot.removeAll(miniListAutoBot);
			miniListDecepticon.removeAll(miniListDecepticon);
			transformersMap.remove(autoBotListTransformers);
			transformersMap.remove(decepticonsTransformers);
			transformersMap.values().removeAll(getAllTransformers());
			outputList.add("Its a tie");
		}
		
		outputList.add("Number of Battles : "+String.valueOf(battles));
		
		objItrSurvivorMap = survivorMap.entrySet().iterator();
		while(objItrSurvivorMap.hasNext()){
			Entry<String, String> entrySurvivor = objItrSurvivorMap.next();
			outputList.add("Survivor from the losing team ( "+entrySurvivor.getKey() + " )" +entrySurvivor.getValue());
		}
		
		System.out.println("outputList : "+outputList);
		
		itrAutobots =null;
		itrDecepticons=null;
		objSet = null;
		objItr = null;
		autoBotListTransformers=null;
		decepticonsTransformers=null;
		miniListAutoBot = null;
		miniListDecepticon = null;
		
		return outputList;
	}
	
}
