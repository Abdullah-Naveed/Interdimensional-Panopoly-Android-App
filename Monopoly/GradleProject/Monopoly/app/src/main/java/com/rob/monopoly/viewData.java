package com.rob.monopoly;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author ani
 */
public class viewData {


    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> property = new ArrayList<>();
    ArrayList<String> value = new ArrayList<>();

    Context context;

    public viewData(Context context)
    {
        this.context=context;
    }

    public void loadSingleValuedData(String filepath) {
        try {
            String line;
//            FileInputStream fis = new FileInputStream(filepath);
            InputStream is = context.getAssets().open("singlevalued.tsv");
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            ArrayList<String> features = new ArrayList<>();
            features.add("name");
            features.add("canonical_name");
            features.add("gender");
            features.add("address_1");
            features.add("address_2");
            features.add("address_3");
            features.add("politics");
            features.add("marital_Status");
            features.add("fictive_status");

            while ((line = br.readLine()) != null) {
                String[] tokens = line.trim().split("\t");

                for (int i = 1; i < tokens.length; i++) {
                    this.name.add(tokens[0]);
                    this.property.add(features.get(i));
                    this.value.add(tokens[i]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMultiValuedData(String filepath) {
        try {
            String line;
            InputStream is = context.getAssets().open("multivalued.tsv");
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            while ((line = br.readLine()) != null) {
                String[] tokens = line.trim().split("\t");
                this.name.add(tokens[0]);
                this.property.add(tokens[1]);
                this.value.add(tokens[2]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Set<Integer> query(String qname, String qproperty, String qvalue) {
        Set<Integer> name_result_index = new HashSet<>();
        Set<Integer> property_result_index = new HashSet<>();
        Set<Integer> value_result_index = new HashSet<>();

        if (qname != "NA") {
            for (int i = 0; i < this.name.size(); i++) {
                if (this.name.get(i).equals(qname)) {
                    name_result_index.add(i);
                }
            }
        }
        if (qproperty != "NA") {
            for (int i = 0; i < this.property.size(); i++) {
                if (this.property.get(i).equals(qproperty)) {
                    property_result_index.add(i);
                }
            }
        }

        if (qvalue != "NA") {
            for (int i = 0; i < this.value.size(); i++) {
                if (this.value.get(i).equals(qvalue)) {
                    value_result_index.add(i);
                }
            }
        }

        Set<Integer> result_index = new HashSet<>();

        result_index.addAll(name_result_index);
        result_index.addAll(property_result_index);
        result_index.addAll(value_result_index);
        
        if (name_result_index.size() > 0) {
            result_index.retainAll(name_result_index);
        }
        if (property_result_index.size() > 0) {
            result_index.retainAll(property_result_index);
        }
        if (value_result_index.size() > 0) {
            result_index.retainAll(value_result_index);
        }
        
        return result_index;
    }

    public ArrayList<ArrayList<String>> get_data(String qname, String qproperty, String qvalue) {
        Set<Integer> query = query(qname, qproperty, qvalue);
        
        ArrayList<ArrayList<String>> output = new ArrayList<>();
        for (Integer i : query) {
            ArrayList<String> tuple = new ArrayList<>();
            tuple.add(this.name.get(i));
            tuple.add(this.property.get(i));
            tuple.add(this.value.get(i));
            output.add(tuple);
        }
        return output;
    }

//

}

   // public ArrayList<Set<String>> populateProperties(){
//        viewData database = new viewData(this);
//        database.loadMultiValuedData("");
//        ArrayList<ArrayList<String>> al = new ArrayList<>();
//        ArrayList<ArrayList<ArrayList<String>>> al2 = new ArrayList<>();
//        ArrayList<String> fictionalWorldsAL = new ArrayList<>();
//        ArrayList<String> charactersAL = new ArrayList<>();
//
//
//
//        //getting all fictional worlds
//        al.addAll(database.get_data("NA","Fictional World","NA"));
//        for(int i=0; i<al.size(); i++){
//            fictionalWorldsAL.add(al.get(i).get(2));
//        }
//        al.clear();
//
//        for(String str : fictionalWorldsAL){
//            al2.add(database.get_data("NA","NA",str));
//        }
//
//        //all the characters in the fictional worlds
//        for(int i=0; i<al2.size(); i++){
//            charactersAL.add(al2.get(i).get(0).get(0));
//        }
//
//        for(int i=0; i<al2.size(); i++){
//            if(database.get_data("NA","NA","NA"){
//                charactersAL.add(al.get(i).get(0));
//
//            }
//        }
//
//
//
//
//
//
////        for(String str : charactersAL){
////            System.out.println(str);
////        }
//
//
//        return null;
//    }
