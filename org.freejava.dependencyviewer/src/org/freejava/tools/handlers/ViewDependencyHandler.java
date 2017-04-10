package org.freejava.tools.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.freejava.dependency.graphbuilder.impl.GraphBuilderFacadeImpl;
import org.freejava.dependency.model.Graph;
import org.freejava.dependency.model.Name;
import org.freejava.dependency.model.ViewCommand;
import org.freejava.tools.Activator;
import org.freejava.tools.handlers.dependency.DependencyView;

/**
 * Handler class for View Class/Package Dependency action.
 *
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class ViewDependencyHandler extends AbstractHandler {

    /**
     * the command has been executed, so extract extract the needed information
     * from the application context.
     */
    public Object execute(ExecutionEvent event) throws ExecutionException {
        try {
            ISelection selection = HandlerUtil.getCurrentSelection(event);

            if (!(selection instanceof IStructuredSelection)) {
                return null;
            }

            // map selection+command -> graph
            final Graph<Name> graphNodes = new GraphBuilderFacadeImpl(new ViewCommand(event.getCommand()), (IStructuredSelection) selection).build();

            getDependencyView().setDependencyInfo(graphNodes);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }



	private DependencyView getDependencyView() {

		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

		// Send the result to the Dependency view and show that view to user
		IViewReference[] viewRefs =  page.getViewReferences();
		DependencyView  dependencyView = null;
		for (IViewReference viewRef : viewRefs) {
		    if (viewRef.getId().equals(DependencyView.ID)) {
		        try {
		            dependencyView = (DependencyView) viewRef.getPage().showView(DependencyView.ID, null, IWorkbenchPage.VIEW_ACTIVATE);
		        } catch (PartInitException e) {
		            Activator.logError("Cannot show view " + DependencyView.ID, e);				}
		    }
		}
		if (dependencyView == null) {
		    try {
		        dependencyView = (DependencyView) page.showView(DependencyView.ID, null, IWorkbenchPage.VIEW_ACTIVATE);
		    } catch (Exception e) {
		        Activator.logError("Cannot show view " + DependencyView.ID, e);
		    }
		}
		return dependencyView;
	}



}
