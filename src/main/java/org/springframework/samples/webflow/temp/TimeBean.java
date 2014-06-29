package org.springframework.samples.webflow.temp;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.Calendar;

/**
 * Created by orcwarrior on 2014-06-23.
 */
@ManagedBean(name = "timeBean")
@RequestScoped
public class TimeBean {

    public String time;
    public String getTime(){
        return Calendar.getInstance().getTime().toString();
    }
}
