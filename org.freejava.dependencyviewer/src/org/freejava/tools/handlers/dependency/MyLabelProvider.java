package org.freejava.tools.handlers.dependency;

import org.eclipse.draw2d.IFigure;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.freejava.dependency.builder.Name;
import org.freejava.dependency.graph.Vertex;
import org.freejava.tools.Activator;

public class MyLabelProvider extends LabelProvider implements org.eclipse.zest.core.viewers.IEntityStyleProvider {

    public Image getImage(Object element) {

        Image image = null;
        if (element instanceof Vertex) {
            @SuppressWarnings("unchecked")
            Vertex<Name> node = (Vertex<Name>) element;
            if (node.getNode().isClass()) {
                image = Activator.getDefault().getImageRegistry().get("class");
            } else if (node.getNode().isInterface()) {
                    image = Activator.getDefault().getImageRegistry().get("interface");
            } else {
                image = Activator.getDefault().getImageRegistry().get("package");
            }
        }
        return image;
    }

    public String getText(Object element) {
        String name = null;
        if (element instanceof Vertex) {
            @SuppressWarnings("unchecked")
            Vertex<Name> node = (Vertex<Name>) element;
            if (node.getNode().getName() == null || node.getNode().getName().equals("")) {
                name = "<default>";
            } else {
                name = node.getNode().getName();
            }
        }
        return name;
    }

	public Color ORANGE = new Color(Display.getDefault(), 255, 196, 0);

	public boolean fisheyeNode(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public Color getBackgroundColour(Object element) {
        if (element instanceof Vertex) {
            @SuppressWarnings("unchecked")
            Vertex<Name> node = (Vertex<Name>) element;
            if (node.getNode().isFoundViaDependency()) {
            	return ORANGE;
            }
        }
		// TODO Auto-generated method stub
		return null;
	}

	public Color getBorderColor(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Color getBorderHighlightColor(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getBorderWidth(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Color getForegroundColour(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Color getNodeHighlightColor(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public IFigure getTooltip(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}