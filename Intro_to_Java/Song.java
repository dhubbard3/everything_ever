/**********************************************************************

CSC110 Programming Assignment #1
Dave Hubbard             1/29/09

This program uses static methods to produce
song lyrics while avoiding redundancy.

**********************************************************************/




public class Song{
	public static void main (String[] args){
		System.out.println("There was an old woman who swallowed a fly.");
		endVerse();
		spiderVerse();
		birdVerse();
		catVerse();
		dogVerse();
		horseVerse();

	}

	public static void endVerse(){
		System.out.println("I don't know why she swallowed a fly,");
		System.out.println("Perhaps she'll die.");
		System.out.println();
	}

	public static void spiderVerse(){
		System.out.println("There was an old woman who swallowed a spider,");
		System.out.println("That wriggled and wriggled and jiggled inside her.");
		swallowSpider();

	}

	public static void birdVerse(){
		System.out.println("There was an old woman who swallowed a bird,");
		System.out.println("How absurd to swallow a bird.");
		swallowBird();

	}

	public static void catVerse(){
		System.out.println("There was an old woman who swallowed a cat,");
		System.out.println("Imagine that to swallow a cat.");
		swallowCat();

	}

	public static void dogVerse(){
		System.out.println("There was an old woman who swallowed a dog,");
		System.out.println("What a hog to swallow a dog.");
		swallowDog();

	}

	public static void horseVerse(){
		System.out.println("There was an old woman who swallowed a horse,");
		System.out.println("She died of course.");

	}

	public static void swallowSpider(){
		System.out.println("She swallowed the spider to catch the fly,");
		endVerse();
	}

	public static void swallowBird(){
		System.out.println("She swallowed the bird to catch the spider,");
		swallowSpider();
	}

	public static void swallowCat(){
		System.out.println("She swallowed the cat to catch the bird,");
		swallowBird();
	}

	public static void swallowDog(){
		System.out.println("She swallowed the dog to catch the cat,");
		swallowCat();
	}

}