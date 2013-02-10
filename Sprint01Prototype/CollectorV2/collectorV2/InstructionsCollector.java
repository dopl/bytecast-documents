/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
/**
 *
 * @author Harsh
 */

public class InstructionsCollector {
    public static void main(String a[]) throws FileNotFoundException, IOException{
        
        Set<String> result = new TreeSet<String>();
        for(int i=0;i<a.length;++i)
        {
            String path = a[i];
            if(i!=(a.length-1) && a[i+1].startsWith("/dfs"))
            {
                String fns[] = a[i+1].split(":")[1].split(",");
                result.addAll(collect(path, true, fns));
                ++i;
            }else
            {
               result.addAll(collect(path, false, null));
            }
        }
        
        for(String line: result)
        {
            System.out.println(line);
        }
        
        
    }
    
   private static void dfs(Stack<String> st,
                    Map<String,List<String>> sections,
                    Set<String> visited,
                    Set<String> allFunctionsCalled)
    {
        if(st.isEmpty())
            return;
       String sec =  st.pop(); 
       visited.add(sec);
       allFunctionsCalled.add(sec);
       Set<String> children = getChildren(sections, sec);
       for(String child:children)
       {
           if(!( visited.contains(child)))
            st.push(child);
       }
       dfs(st,sections,visited,allFunctionsCalled);
        
    }

    private static Set<String> getChildren(Map<String, List<String>> sections, String sec) {
        List<String> s = sections.get(sec);
        Set<String> children = new LinkedHashSet<String>();
        for(String line: s)
        {
             String in[] = line.split("\t");
             if(in.length == 3 && in[2].contains("callq") )
             {
                 if(in[2].split(" ").length==4 && !(in[2].contains("+")) )
                 {
                       String name = in[2].split(" ")[3];
                       children.add(name);
                 }
                     
             }
        }
        return children;
    }

    private static Set<String> collect(String path, boolean isDFS,String[] functionNames) throws IOException, FileNotFoundException {
        
        FileReader fr = new FileReader(new File(path));
        BufferedReader br = new BufferedReader(fr);
        Map<String,List<String>> sections = new HashMap<String,List<String>>();
        List<String> list = null;
        String line="";
       
        while((line =br.readLine()) !=null)
        {
            if(line.startsWith("00") )
            {
              list = new ArrayList<String>();
              sections.put(line.split(" ")[1].split(":")[0], list);
              continue;
            }
            if(list!=null)
               list.add(line);
        }
        Set<String> set = new HashSet<String>();
        if(isDFS)
        {
            for(String rootsection: functionNames)
            {
                set.addAll(doDFSCollection(sections,rootsection));
            }
        }else
        {
            set.addAll(doNormalCollection(sections));
        }
        
        return set;
    }

    private static Set<String> doDFSCollection(Map<String, List<String>> sections,String rootsection) {
        Stack<String> st = new Stack<String>();
        st.push(rootsection);
        Set<String> allFunctionsCalled = new LinkedHashSet<String>();
        dfs(st,sections,new HashSet<String>(),allFunctionsCalled);
        Set<String> set = new TreeSet<String>();
        for(String fn : allFunctionsCalled)
        {
            List<String> ins = sections.get(fn);
            
            for(String instr: ins)
            {
                String in[] = instr.split("\t");
                if(in.length == 3 )
                {
                    set.add(in[2].split(" ")[0]);
                }
            }
        }
        return set;
    }

    private static Set<String> doNormalCollection(Map<String, List<String>> sections) {
        Set<String> set = new HashSet<String>();
        for(String key: sections.keySet())
        {
            List<String> ins = sections.get(key);
            for(String line: ins)
            {
                 String in[] = line.split("\t");
                if(in.length == 3 )
                {
                    set.add(in[2].split(" ")[0]);
                }
            }
        }
        return set;
    }

}
