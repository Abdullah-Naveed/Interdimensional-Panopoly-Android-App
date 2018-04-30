package com.rob.monopoly.NOCList.twitterbotics;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

import com.rob.monopoly.NOCList.tabular.BucketTable;


// A class to hold a particular module of a knowledge-base of triples
// T. Veale, 2015


public class KnowledgeBaseModule {
	final static String kdir = "";
	static Context context = null;
	private static Vector<String> fictionalWorlds = new Vector<>();


	private static Random RND = new Random();

	private Hashtable kb = new Hashtable();

	private Vector fieldNames = new Vector();
	private Vector fieldTables = new Vector();

	//-----------------------------------------------------------------------------------------------//
	//-----------------------------------------------------------------------------------------------//
	//   Constructors
	//-----------------------------------------------------------------------------------------------//
	//-----------------------------------------------------------------------------------------------//

	public KnowledgeBaseModule(String filename, int keyPosition) {
		loadKnowledgeBaseFrom(filename, keyPosition);
	}

	public KnowledgeBaseModule(String filename) {
		loadKnowledgeBaseFrom(filename, 0);
	}

	public KnowledgeBaseModule(Context context) {
		this.context = context;
//		try {
//			InputStream is=context.getAssets().open("FictionalWorlds.txt");
//			BufferedReader br = new BufferedReader(new InputStreamReader(is));
//			is.close();
//			String  line;
//
//			while((line = br.readLine())!=null){
//				fictionalWorlds.add(line.trim());
//			}
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}


		try {
//			AssetManager assetManager=context.getAssets();
//			System.out.println(assetManager.getLocales());
			InputStream is = context.getAssets().open("Veales_The_NOC_List.txt");
			loadKnowledgeBaseFrom(is, 0);

//			BufferedReader br = new BufferedReader(new InputStreamReader(is));
//
//			String line;
//
//			while ((line = br.readLine()) != null) {
//				fictionalWorlds.add(line.trim());
//			}
//
//			for (int i=1;i<fictionalWorlds.size();i++) {
//				String[] tokens = fictionalWorlds.get(i).split("\\t");
//				for(String token: tokens){
//
//
////					System.out.println(token);
//				}
//				break;
//
//			}
//
//			br.close();
//			is.close();
//
//
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//-----------------------------------------------------------------------------------------------//
	//-----------------------------------------------------------------------------------------------//
	//  Accessors
	//-----------------------------------------------------------------------------------------------//
	//-----------------------------------------------------------------------------------------------//

	// Get the list of fields that describe concepts (keys) in this knowledge-base module

	public Vector<String> getFieldNames() {
		return fieldNames;
	}

	public Vector<String> getFictionalWorlds(Context context) {
		try {
			InputStream is = context.getAssets().open("FictionalWorlds.txt");
			BufferedReader input = new BufferedReader(new InputStreamReader(is, "UTF8"));
			String line;
			while ((line = input.readLine()) != null)  // Read a line at a time
			{
				fictionalWorlds.add(line);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return fictionalWorlds;
	}


//	public ArrayList<ArrayList<String>> getGroupLocations(Context context){
//
//		ArrayList<String> genres = new ArrayList<>();
//		Vector<String> Characters = new Vector<>();
//
//		for(int i = 0; i<8; i++){
//			String Worlds = this.selectRandomlyFrom(this.getFictionalWorlds(context));
//			Characters = this.getAllKeysWithFieldValue("Fictional World", Worlds);
//
//			while (Characters.size() < 3) {
//				Worlds = this.selectRandomlyFrom(this.getFictionalWorlds(context));
//				Characters = this.getAllKeysWithFieldValue("Fictional World", Worlds);
//
//			}
//
//			genres.add(Characters.firstElement());
//
//		}
//
//		String Char1 = this.selectRandomlyFrom(Characters);
//
//		Vector<String> Address = this.getFieldValues("Adress 1", Char1);
//
//		if(Address == null) {
//
//			Address = this.getFieldValues("Address 2", Char1);
//		}
//
//		if(Address == null) {
//
//			Address = this.getFieldValues("Address 3", Char1);
//		}
//
//
//		return Address;
//
//}


	// Get the values associated with a specific field of a key concept

	public Vector<String> getFieldValues(String fieldName, String key) {
		for (int f = 0; f < fieldNames.size(); f++) {
			if (fieldName.equals(fieldNames.elementAt(f))) {
				Hashtable field = (Hashtable) fieldTables.elementAt(f);

				return (Vector) field.get(key);
			}
		}

		return null;
	}


	public String getFirstValue(String fieldName, String key) {
		Vector values = getFieldValues(fieldName, key);

		if (values == null || values.size() < 1)
			return null;
		else
			return (String) values.elementAt(0);
	}


	// Check whether a key concept has a given value for a given field

	public boolean hasFieldValue(String fieldName, String key, String value) {
		for (int f = 0; f < fieldNames.size(); f++) {
			if (fieldName.equals(fieldNames.elementAt(f))) {
				Hashtable field = (Hashtable) fieldTables.elementAt(f);

				Vector values = (Vector) field.get(key);

				if (values == null || values.size() == 0)
					return false;

				for (int v = 0; v < values.size(); v++)
					if (value.equals(values.elementAt(v)))
						return true;
			}
		}

		return false;
	}

	// Return a list of all the key concepts that have fields/values in this knowledge module

	public Vector<String> getAllFrames() {
		Vector longest = null;

		for (int f = 0; f < fieldTables.size(); f++) {
			Hashtable field = (Hashtable) fieldTables.elementAt(f);

			Vector list = (Vector) field.get("*keylist*");

			if (list != null && (longest == null || list.size() > longest.size()))
				longest = list;
		}

		return longest;
	}

	// return a list of key concepts with a given value in a given field

	public Vector<String> getAllKeysWithFieldValue(String fieldname, String value) {
		Vector<String> matchingKeys = new Vector();

		if (value == null)
			return matchingKeys;
		else
			value = value.intern();

		for (int f = 0; f < fieldTables.size(); f++) {
			String name = (String) fieldNames.elementAt(f);

			if (!name.equals(fieldname)) continue;

			Hashtable field = (Hashtable) fieldTables.elementAt(f);

			Vector keys = (Vector) field.get("*keylist*");

			if (keys == null) break;

			for (int k = 0; k < keys.size(); k++) {
				String key = (String) keys.elementAt(k);

				Vector values = (Vector) field.get(key);

				if (values != null && values.contains(value))
					matchingKeys.add(key);
			}
		}

		return matchingKeys;
	}


	public String selectRandomlyFrom(Vector<String> choices) {
		if (choices == null || choices.size() == 0)
			return null;
		else
			return choices.elementAt(RND.nextInt(choices.size()));
	}

	//-----------------------------------------------------------------------------------------------//
	//-----------------------------------------------------------------------------------------------//
	//   Useful public tools
	//-----------------------------------------------------------------------------------------------//
	//-----------------------------------------------------------------------------------------------//

	// Turn a field value into a hashtag

	public String hashtagify(String phrase) {
		if (phrase == null || phrase.length() < 1)
			return phrase;

		if (phrase.indexOf((int) ' ') < 0)
			return "#" + Character.toUpperCase(phrase.charAt(0)) + phrase.substring(1);

		StringBuffer tagged = new StringBuffer("#");

		char prev = ' ', curr = ' ';

		for (int i = 0; i < phrase.length(); i++) {
			prev = curr;
			curr = phrase.charAt(i);

			if ((prev == ' ' || prev == '.' || prev == '_') && Character.isLowerCase(curr))
				curr = Character.toUpperCase(curr);

			if (curr != ' ' && curr != '\"' && curr != '.' && curr != '-' && curr != '\'')
				tagged.append(curr);
		}

		return tagged.toString();
	}


	public String replaceWith(String whole, String before, String after) {
		int where = whole.indexOf(before);

		while (where >= 0) {
			whole = whole.substring(0, where) + after + whole.substring(where + before.length());

			where = whole.indexOf(before, where + after.length());
		}

		return whole;
	}


	// Get the intersection of two lists of concepts

	public Vector intersect(Vector v1, Vector v2) {
		if (v1 == null || v1.size() == 0)
			return null;

		if (v2 == null || v2.size() == 0)
			return null;

		if (v1.size() * v2.size() < 1000) {
			Vector common = new Vector();

			for (int i = 0; i < v1.size(); i++)
				if (v2.contains(v1.elementAt(i)))
					common.add(v1.elementAt(i));

			return common;
		}

		Hashtable seen = new Hashtable();

		for (int i = 0; i < v2.size(); i++)
			seen.put(v2.elementAt(i), "seen");

		Vector common = new Vector();

		for (int i = 0; i < v1.size(); i++)
			if (seen.get(v1.elementAt(i)) != null)
				common.add(v1.elementAt(i));

		return common;
	}


	// Get the union of two lists of concepts

	public Vector union(Vector v1, Vector v2) {
		if (v1 == null || v1.size() == 0)
			return v2;

		if (v2 == null || v2.size() == 0)
			return v1;

		Hashtable seen = new Hashtable();

		Vector union = new Vector();


		for (int i = 0; i < v1.size(); i++) {
			seen.put(v1.elementAt(i), "seen");
			union.add(v1.elementAt(i));
		}

		for (int i = 0; i < v2.size(); i++)
			if (seen.get(v2.elementAt(i)) != null)
				union.add(v2.elementAt(i));

		return union;
	}


	// Get the union of two lists of concepts

	public Vector difference(Vector v1, Vector v2) {
		if (v1 == null || v1.size() == 0)
			return null;

		if (v2 == null || v2.size() == 0)
			return v1;

		Hashtable seen = new Hashtable();

		Vector difference = new Vector();


		for (int i = 0; i < v2.size(); i++)
			seen.put(v2.elementAt(i), "seen");

		for (int i = 0; i < v1.size(); i++)
			if (seen.get(v1.elementAt(i)) == null)
				difference.add(v1.elementAt(i));

		return difference;
	}

	//-----------------------------------------------------------------------------------------------//
	//-----------------------------------------------------------------------------------------------//
	//   Invert a Field to get a table mapping from values to key concepts
	//     e.g. invert the Positive Talking Points field to map from positive adjectives to people
	//-----------------------------------------------------------------------------------------------//
	//-----------------------------------------------------------------------------------------------//

	public Hashtable getInvertedField(String givenField) {
		return getInvertedField(givenField, new Hashtable());
	}


	public Hashtable getInvertedField(String givenField, Hashtable inversion) {
		Vector invertedKeys = new Vector();

		inversion.put("*keylist*", invertedKeys);

		for (int f = 0; f < fieldTables.size(); f++) {
			String fieldName = (String) fieldNames.elementAt(f);

			if (!fieldName.equals(givenField))
				continue;

			Hashtable fieldTable = (Hashtable) fieldTables.elementAt(f);

			Vector keylist = (Vector) fieldTable.get("*keylist*"), values = null, entries = null;

			if (keylist == null) continue;

			String key = null, value = null;

			for (int k = 0; k < keylist.size(); k++) {
				key = (String) keylist.elementAt(k);
				values = (Vector) fieldTable.get(key);

				if (values == null) continue;

				for (int v = 0; v < values.size(); v++) {
					value = (String) values.elementAt(v);

					entries = (Vector) inversion.get(value);

					if (entries == null) {
						entries = new Vector();
						inversion.put(value, entries);

						invertedKeys.add(value);
					}

					if (!entries.contains(key))
						entries.add(key);
				}
			}
		}

		return inversion;
	}


	public BucketTable invertFieldInto(String givenField, BucketTable inversion) {
		Vector invertedKeys = new Vector();

		inversion.put("*keylist*", invertedKeys);

		for (int f = 0; f < fieldTables.size(); f++) {
			String fieldName = (String) fieldNames.elementAt(f);

			if (!fieldName.equals(givenField))
				continue;

			Hashtable fieldTable = (Hashtable) fieldTables.elementAt(f);

			Vector keylist = (Vector) fieldTable.get("*keylist*"), values = null, entries = null;

			if (keylist == null) continue;

			String key = null, value = null;

			for (int k = 0; k < keylist.size(); k++) {
				key = (String) keylist.elementAt(k);
				values = (Vector) fieldTable.get(key);

				if (values == null) continue;

				for (int v = 0; v < values.size(); v++) {
					value = (String) values.elementAt(v);

					inversion.put(value, key);
				}
			}
		}

		return inversion;
	}


	//-----------------------------------------------------------------------------------------------//
	//-----------------------------------------------------------------------------------------------//
	//   Return an ordered list of the most similar key concepts to a given key, most similar first
	//-----------------------------------------------------------------------------------------------//
	//-----------------------------------------------------------------------------------------------//

	public Vector getSimilarConcepts(String given) {
		return getSimilarConcepts(given, fieldNames, 1);
	}


	public Vector getSimilarConcepts(String given, int minSimilarity) {
		return getSimilarConcepts(given, fieldNames, minSimilarity);
	}


	public Vector getSimilarConcepts(String given, Vector fieldNames) {
		return getSimilarConcepts(given, fieldNames, 1);
	}


	public Vector getSimilarConcepts(String given, Vector fieldNames, int minSimilarity) {
		Vector keys = getAllFrames();

		Vector[] scale = new Vector[fieldNames.size() * 10];

		String other = null;

		for (int k = 0; k < keys.size(); k++) {
			other = (String) keys.elementAt(k);

			if (other.equals(given)) continue;

			Vector overlap = getOverlappingFields(given, other, fieldNames);

			if (overlap == null || overlap.size() < minSimilarity) continue;

			int simScore = overlap.size();

			if (simScore >= scale.length) simScore = scale.length - 1;

			if (scale[simScore] == null)
				scale[simScore] = new Vector();

			scale[simScore].add(other);
		}

		Vector rank = new Vector();

		for (int s = scale.length - 1; s > 0; s--) {
			if (scale[s] == null) continue;

			for (int v = 0; v < scale[s].size(); v++)
				rank.add(scale[s].elementAt(v));
		}

		return rank;
	}

	//-----------------------------------------------------------------------------------------------//
	//-----------------------------------------------------------------------------------------------//
	//   Return a list of overlapping fieldname:fieldvalue pairs for two key concepts
	//-----------------------------------------------------------------------------------------------//
	//-----------------------------------------------------------------------------------------------//

	public Vector getOverlappingFields(String key1, String key2) {
		return getOverlappingFields(key1, key2, fieldNames);
	}


	public Vector getOverlappingFields(String key1, String key2, Vector salient) {
		String fieldName = null;
		Hashtable fieldTable = null;

		Vector overlap = new Vector();

		for (int n = 0; n < fieldNames.size(); n++) {
			fieldName = (String) fieldNames.elementAt(n);

			if (salient != fieldNames && !salient.contains(fieldName))
				continue;

			fieldTable = (Hashtable) fieldTables.elementAt(n);

			Vector common = intersect((Vector) fieldTable.get(key1), (Vector) fieldTable.get(key2));

			if (common == null) continue;

			for (int c = 0; c < common.size(); c++) {
				overlap.add(fieldName + "=" + (String) common.elementAt(c));
			}
		}

		return overlap;
	}


	//-----------------------------------------------------------------------------------------------//
	//-----------------------------------------------------------------------------------------------//
	//   Load Knowledge-Base Module from a Given File
	//-----------------------------------------------------------------------------------------------//
	//-----------------------------------------------------------------------------------------------//

	private void loadKnowledgeBaseFrom(String filename) {
		loadKnowledgeBaseFrom(filename, 0);
	}


	private void loadKnowledgeBaseFrom(String filename, int keyPosition) {
//		InputStream input;

//		    input = new FileInputStream(filename);


		try {
//			AssetManager assetManager=context.getAssets();
//			System.out.println(assetManager.getLocales());
			InputStream is = context.getAssets().open(filename);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			String line;

			while ((line = br.readLine()) != null) {
				fictionalWorlds.add(line.trim());
			}

			for (String s : fictionalWorlds) {
//				String[] strings=s.split("\\t");

			}

			br.close();
			is.close();

			loadKnowledgeBaseFrom(is, keyPosition);

		} catch (IOException e) {
			e.printStackTrace();
		}


	}


	private void loadKnowledgeBaseFrom(InputStream stream, int keyPosition) {
		String line = null;

		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(stream, "UTF8"));

			loadFieldNames(input.readLine());

			while ((line = input.readLine()) != null)  // Read a line at a time
			{
				parseFieldsIntoKB(fieldNames, line, keyPosition);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	private void parseFieldsIntoKB(Vector fieldNames, String line, int keyPosition) {
		StringTokenizer values = new StringTokenizer(line, "\t", true);

		int fieldNumber = 0;

		Vector valueSets = new Vector();

		for (int f = 0; f < fieldNames.size(); f++)
			valueSets.add("");

		while (values.hasMoreTokens()) {
			String token = values.nextToken();

			if (token.equals("\t")) {
				fieldNumber++;

				if (fieldNumber >= valueSets.size())
					break;
				else
					continue;
			}

			token = token.trim();

			if (token.length() > 0)
				valueSets.setElementAt(token, fieldNumber);
		}

		String key = ((String) valueSets.elementAt(keyPosition)).trim().intern();

		for (int v = 0; v < valueSets.size(); v++) {
			setFieldsInKB((Hashtable) fieldTables.elementAt(v), key, (String) valueSets.elementAt(v));
		}
	}


	private Vector setFieldsInKB(Hashtable field, String key, String valueSet) {
		if (valueSet == "") return null;

		StringTokenizer values = new StringTokenizer(valueSet, ",", false);

		Vector fieldValues = (Vector) field.get(key);

		if (fieldValues == null) {
			Vector keyList = (Vector) field.get("*keylist*");

			if (keyList == null) {
				keyList = new Vector();
				field.put("*keylist*", keyList);
			}

			keyList.add(key);

			fieldValues = new Vector();
		}

		field.put(key, fieldValues);

		while (values.hasMoreTokens()) {
			String value = values.nextToken().trim().intern();

			if (value.length() > 0 && !fieldValues.contains(value))
				fieldValues.add(value);
		}

		return fieldValues;
	}


	private Vector loadFieldNames(String line) {
		StringTokenizer names = new StringTokenizer(line, "\t");

		fieldNames.setSize(0);
		fieldTables.setSize(0);

		String previous = "", current = "";

		Hashtable prevTable = null, currTable = null;

		while (names.hasMoreTokens()) {
			previous = current;
			current = names.nextToken().intern();

			prevTable = currTable;
			currTable = new Hashtable();

			fieldNames.add(current);

			if (current == previous && prevTable != null)
				currTable = prevTable;

			fieldTables.add(currTable);
		}

		return fieldNames;
	}


	public HashMap getLocations(Context context) {

		Set<String> updatedWorlds = new HashSet<>();
		Set<String> uniqueAddresses = new HashSet<>();
		Vector<String> Characters = new Vector<>();
		Vector<String> Worlds;
		Vector<String> Address;
		String RandomWorld = "";
		HashMap<String, ArrayList<String>> colourLocations = new HashMap<>();

		Worlds = this.getFictionalWorlds(context);

		for (String str : Worlds) {
			Characters = this.getAllKeysWithFieldValue("Fictional World", str);

			if (Characters.size() > 2) {
				updatedWorlds.add(str);
			}
		}

		Characters.clear();

		Vector<String> updatedWorldsVector = new Vector<>(updatedWorlds);

		while (colourLocations.size() < 8) {

			uniqueAddresses.clear();

			while (uniqueAddresses.size() < 3) {

				if (uniqueAddresses.size() < 3) {
					uniqueAddresses.clear();
				}

				RandomWorld = this.selectRandomlyFrom(updatedWorldsVector);
				Characters = this.getAllKeysWithFieldValue("Fictional World", RandomWorld);
				updatedWorldsVector.removeElement(RandomWorld);

				for (String Character : Characters) {

					Address = this.getFieldValues("Address 1", Character);

					if (Address == null) {

						Address = this.getFieldValues("Address 2", Character);
					}

					if (Address == null) {

						Address = this.getFieldValues("Address 3", Character);
					}

					if (Address == null)
						continue;

					uniqueAddresses.add(Address.get(0));


				}
			}

			ArrayList<String> uniqueAddressAL = new ArrayList<>(uniqueAddresses);

			colourLocations.put(RandomWorld, uniqueAddressAL);
		}

//		System.out.println(this.getAllKeysWithFieldValue("Negative Talking Points", "fat"));

		return colourLocations;
	}

}