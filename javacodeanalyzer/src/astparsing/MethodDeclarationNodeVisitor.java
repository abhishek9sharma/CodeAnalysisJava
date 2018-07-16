package astparsing;


import java.util.ArrayList;
import java.util.List;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;


public class MethodDeclarationNodeVisitor extends ASTVisitor {

	List<MethodDeclaration> methodNodeList = new ArrayList<>();

	public List<MethodDeclaration> getMethodList() {
		return methodNodeList;
	}

	@Override
	public boolean visit(MethodDeclaration node) {
		methodNodeList.add(node);
		return true;
	}

}
