
public class FamilyTreeTest {
       public static void main(String[] args) {
        //user must input an ancestors name to begin the family tree
        String name = Input.getString("input the ancestors name: ");
        FamilyTree familytree = new FamilyTree(name);
        Integer option;
        //a menu is brought up with options that the user can choose from
        do {
            System.out.println("0: quit");
            System.out.println("1: add child to current");
            System.out.println("2: add partner to ancestor");           
            System.out.println("3: find family member");
            System.out.println("4: display current");
            System.out.println("5: display familytree");
            option = Input.getInteger("input option: ");
            switch (option) {
                case 1:
                    //user inputs the name of the child
                    name = Input.getString("input the child's name: ");
                    familytree.addChild(name);
                    break;
                case 2: 
                    //user inputs the partners name
                    name = Input.getString("input the partners's name: ");
                    familytree.addPartner(name);
                    break;
                case 3:
                    //user inputs family members name to find them in the family tree
                    name = Input.getString("input the family member's name: ");
                    try{
                        familytree.findFamilyMember(name);
                        System.out.println(familytree.getCurrent());
                    } catch(FamilyTree.FamilyMemberNotFoundException e){
                        System.out.println("not found");
                    } 
                    break;
                case 4:
                    //current family member is printed
                    System.out.println(familytree.getCurrent());
                    break;
                case 5:
                    //family tree is printed
                    System.out.println(familytree);
                    break;
            }
            //if option 0 is selected then program closes
        } while (option != 0);
    }

}

