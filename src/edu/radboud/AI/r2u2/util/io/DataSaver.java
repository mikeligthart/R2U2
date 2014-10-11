package edu.radboud.ai.r2u2.util.io;

import edu.radboud.ai.r2u2.R2U2Model;
import org.dom4j.Element;

/**
 * Created by Pieter Marsman on 2-7-2014.
 */
public interface DataSaver {

    public void addElement(Element root, R2U2Model model);

    public void readElement(Element element, R2U2Model model);

}
