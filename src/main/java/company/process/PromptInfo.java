package company.process;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/5/20.
 */
public class PromptInfo implements Serializable {
    private String id;
    private String name;
    private String content;

    public PromptInfo() {
    }

    public PromptInfo(String id) {
        this.id = id;
    }

    public PromptInfo(String id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        String desc="PromptInfo{";
        int index=0;
        if(id!=null){
            desc+=" id='" + id + '\'';
            index++;
        }
        if(name!=null){
             if(index==0)
                 desc+="name='" + name + '\'';
             else
                 desc+=",name='" + name + '\'';
        }
        if(content!=null){
            if(index==0)
                desc+="content='" + content + '\'';
            else
                desc+=",content='" + content + '\'';
        }
        desc+='}';
        return desc;
    }
}
