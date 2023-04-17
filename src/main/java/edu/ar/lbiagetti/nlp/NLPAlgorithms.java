package edu.ar.lbiagetti.nlp;

import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.trees.Tree;

import java.util.*;

import org.ejml.simple.SimpleMatrix;

public class NLPAlgorithms {

	public String getSentiment(String input) {
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

		CoreDocument document = new CoreDocument(input);

		pipeline.annotate(document);
		return document.sentences().get(0).sentiment();
	}

	public Tree getSentimentTree(String input) {
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

		CoreDocument document = new CoreDocument(input);
		pipeline.annotate(document);

		CoreSentence sentence = document.sentences().get(0);
		return sentence.sentimentTree();

	}

	public String getSentimentProbability(String input) {
		Tree sentimentTree = getSentimentTree(input);
		SimpleMatrix predictions = RNNCoreAnnotations.getPredictions(sentimentTree);
		StringBuilder strb = new StringBuilder();
		strb.append("TooNeg: ").append(predictions.get(0)).append("- Neg: ").append(predictions.get(1))
				.append("- Neu: ").append(predictions.get(2)).append("- Pos: ").append(predictions.get(3))
				.append("- TooPos: ").append(predictions.get(4));
		return strb.toString();
	}

}
