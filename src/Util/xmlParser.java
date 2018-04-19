package Util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import Dto.Bb_IngredientDto;


public class xmlParser {
  
  static public Map<String, List<Bb_IngredientDto>> xml_getIngredient(String ingredient_xml) {
    
    Map<String, List<Bb_IngredientDto>> ingredient_map = new HashMap<>();
    List<Bb_IngredientDto> bun_list = new ArrayList<>();
    List<Bb_IngredientDto> patty_list = new ArrayList<>();
    List<Bb_IngredientDto> vege_list = new ArrayList<>();
    List<Bb_IngredientDto> etc_list = new ArrayList<>();
    
    try {
            
      File xmlFile = new File(ingredient_xml);
      DocumentBuilderFactory docu_factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docu_builder = docu_factory.newDocumentBuilder();
      Document xmldoc = docu_builder.parse(xmlFile);
      
      NodeList ingredient_list = xmldoc.getElementsByTagName("ingredient");
      
      System.out.println("재료 노드 사이즈 : " + ingredient_list.getLength());
      
      for (int i = 0; i < ingredient_list.getLength(); i++) {
        
        Element ingredient = (Element)ingredient_list.item(i);
        
        int seq = Integer.parseInt(getTagValue(ingredient, "seq"));
        String name = getTagValue(ingredient, "name");
        int types = Integer.parseInt(getTagValue(ingredient, "types"));   
        int price = Integer.parseInt(getTagValue(ingredient, "price"));   
        int cal = Integer.parseInt(getTagValue(ingredient, "cal"));
        int del =  Integer.parseInt(getTagValue(ingredient, "del"));
        String image_Src = getTagValue(ingredient, "img");
        
        switch (types) {
        case 0: bun_list.add(new Bb_IngredientDto(seq, name, types, price, cal, del, image_Src)); break;
        case 1: patty_list.add(new Bb_IngredientDto(seq, name, types, price, cal, del, image_Src)); break;
        case 2: vege_list.add(new Bb_IngredientDto(seq, name, types, price, cal, del, image_Src)); break;
        case 3: etc_list.add(new Bb_IngredientDto(seq, name, types, price, cal, del, image_Src)); break;
        }
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      ingredient_map.put("bun_list", bun_list);
      ingredient_map.put("patty_list", patty_list);
      ingredient_map.put("vege_list", vege_list);
      ingredient_map.put("etc_list", etc_list);
    }
    return ingredient_map;
  }
  
  static public void xml_addIngredient(String ingredient_xml, Bb_IngredientDto ingredient_dto) {
    
    try {     
      File xmlFile = new File(ingredient_xml);
      DocumentBuilderFactory docu_factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docu_builder = docu_factory.newDocumentBuilder();   
      Document xmldoc = docu_builder.parse(xmlFile);
      
      Node parent_node = xmldoc.getElementsByTagName("ingredientlist").item(0); 
      
      parent_node.appendChild(xmldoc.createTextNode("\n  "));
      Element ingredient = xmldoc.createElement("ingredient");
      parent_node.appendChild(ingredient);
      parent_node.appendChild(xmldoc.createTextNode("\n\n"));
      
      
      getIngredient(xmldoc, ingredient, ingredient_dto);
      
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource source = new DOMSource(xmldoc);
      StreamResult result = new StreamResult(new File("D:/Burgerbucks-Customer/WebContent/XML/Ingredient.xml"));
      transformer.transform(source, result);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }
  
  static public void xml_updIngredient(String ingredient_xml, Bb_IngredientDto ingredient_dto) {
    
    try {     
      File xmlFile = new File(ingredient_xml);
      DocumentBuilderFactory docu_factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docu_builder = docu_factory.newDocumentBuilder();   
      Document xmldoc = docu_builder.parse(xmlFile);
      
      NodeList parent_node = xmldoc.getElementsByTagName("ingredientlist");
      Node ingredient_node = null;
      
      System.out.println("테스트 이름 : " + parent_node.item(0).getChildNodes().item(0).getChildNodes().item(0).getNodeName());
      System.out.println("테스트 값 : " + parent_node.item(0).getChildNodes().item(0).getChildNodes().item(0).getNodeValue());
      
      for (int i = 0; i < parent_node.item(0).getChildNodes().getLength(); i++) {
        NodeList ingredeinet_nodelist = parent_node.item(i).getChildNodes();
        
        System.out.println("노드 사이즈 : " + parent_node.getLength());
        
        if ( ingredient_dto.getSeq() == 11){
          ingredient_node = parent_node.item(i);
        }
      }
      
      Element ingredient = xmldoc.createElement("ingredient");
      ingredient_node.appendChild(ingredient);      
      
      getIngredient(xmldoc, ingredient, ingredient_dto);
      
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource source = new DOMSource(xmldoc);
      StreamResult result = new StreamResult(new File("D:/Burgerbucks-Customer/WebContent/XML/Ingredient.xml"));
      transformer.transform(source, result);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }
  
  static public void xml_delIngredient(String ingredient_xml, Bb_IngredientDto ingredient_dto) {
    
    try {     
      File xmlFile = new File(ingredient_xml);
      DocumentBuilderFactory docu_factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docu_builder = docu_factory.newDocumentBuilder();   
      Document xmldoc = docu_builder.parse(xmlFile);
      
      Node parent_node = xmldoc.getElementsByTagName("ingredientlist").item(0); 
      
      parent_node.appendChild(xmldoc.createTextNode("\n  "));
      Element ingredient = xmldoc.createElement("ingredient");
      parent_node.appendChild(ingredient);
      parent_node.appendChild(xmldoc.createTextNode("\n"));
      
      
      getIngredient(xmldoc, ingredient, ingredient_dto);
      
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource source = new DOMSource(xmldoc);
      StreamResult result = new StreamResult(new File("D:/Burgerbucks-Customer/WebContent/XML/asd.xml"));
      transformer.transform(source, result);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }
  
  static public String getTagValue(Element element, String tag) {  
    NodeList nodelist = element.getElementsByTagName(tag).item(0).getChildNodes();
    String value = nodelist.item(0).getNodeValue();
    return value;
  }
  
  static public Node getIngredient(Document xmldoc, Element ingredient, Bb_IngredientDto ingredient_dto) {  
    ingredient.appendChild(xmldoc.createTextNode("\n    "));
    ingredient.appendChild(getIngredientElement(xmldoc, "seq", Integer.toString(ingredient_dto.getSeq())));
    ingredient.appendChild(xmldoc.createTextNode("\n    "));
    ingredient.appendChild(getIngredientElement(xmldoc, "name", ingredient_dto.getName()));
    ingredient.appendChild(xmldoc.createTextNode("\n    "));
    ingredient.appendChild(getIngredientElement(xmldoc, "types", Integer.toString(ingredient_dto.getTypes())));
    ingredient.appendChild(xmldoc.createTextNode("\n    "));
    ingredient.appendChild(getIngredientElement(xmldoc, "price", Integer.toString(ingredient_dto.getPrice())));
    ingredient.appendChild(xmldoc.createTextNode("\n    "));
    ingredient.appendChild(getIngredientElement(xmldoc, "cal", Integer.toString(ingredient_dto.getCal())));
    ingredient.appendChild(xmldoc.createTextNode("\n    "));
    ingredient.appendChild(getIngredientElement(xmldoc, "del", Integer.toString(ingredient_dto.getDel())));
    ingredient.appendChild(xmldoc.createTextNode("\n    "));
    ingredient.appendChild(getIngredientElement(xmldoc, "img", ingredient_dto.getImage_Src()));
    ingredient.appendChild(xmldoc.createTextNode("\n  "));
    return ingredient;
  }
  
  static public Node getIngredientElement(Document xmldoc, String name, String value) {  
    Element node = xmldoc.createElement(name);
    node.appendChild(xmldoc.createTextNode(value));
    return node;
  }

}