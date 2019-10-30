

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class ChatMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//CharStream input = CharStreams.fromString("hello there");
		CharStream input = CharStreams.fromString("john SAYS: hello @michael this will not work\n");
		ChatLexer lexer = new ChatLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		ChatParser parser = new ChatParser(tokens);
		ParseTree tree = parser.chat(); // begin parsing at rule 'r'
		System.out.println(tree.toStringTree(parser)); // print LISP-style tree	
	}
}