package edu.ar.lbiagetti.nlp;

public class App {
	public static void main(String[] args) {
		NLPAlgorithms nlpa = new NLPAlgorithms();
		String text = "You're the best person in the world, i love you, and I'm very very happy about meeting you. You're the best";
		System.out.println(nlpa.getSentiment(text));
		System.out.println(nlpa.getSentimentProbability(text));
		System.out.println(nlpa.getSentimentTree(text));
	}
}
