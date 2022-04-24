
public class FamilyTree {
       public class FamilyMemberNotFoundException extends Exception{}

    private class FamilyTreeNode {
        private String name;
        private FamilyTreeNode ancestor;
        private FamilyTreeNode sibling;
        private FamilyTreeNode children;
        private FamilyTreeNode partner;
    }

    private FamilyTreeNode ancestor;
    private FamilyTreeNode current;

//Sets up exception for a non unique name being input
    public static class NameNotUniqueException extends Exception {}
    public class AlreadyGotAPartnerException extends Exception {}
    public class ChildrenWithNoPartnerException extends Exception {}   

//constructor   
    public FamilyTree(String ancestorName, String partnername) {
        this.ancestor = new FamilyTreeNode();
        this.ancestor.name = ancestorName;
        this.current = this.ancestor;
    }

 //using toString allows a string to be returned that shows the specified inputs 
    public String toString() {
        String familyDetails = new String();
        familyDetails += this.ancestor.name +  getPartner(this.ancestor);
        FamilyTreeNode familymember = this.ancestor.children;
        if (familymember == null) {
            familyDetails += "  has no children\n";        
        } else {
            while (familymember != null) {
                familyDetails += "  " + familymember.name + getPartner(familymember);
                familyDetails += this.getChildren(familymember);
                familymember = familymember.sibling;
            }
        }
        return familyDetails;
    }

//gets information on child
    private String getChildren(FamilyTreeNode familymember) {
        String childrenDetails = new String();
        familymember = familymember.children;
        if (familymember == null) {
            childrenDetails += "    has no children\n";
        } else {
            while (familymember != null) {
                childrenDetails += "    " + familymember.name + getPartner(familymember) +  "\n";
                familymember = familymember.sibling;
            }
        }
        return childrenDetails;
    }

//ability to add child
    public void addChild(String name) {
        FamilyTreeNode familymember = new FamilyTreeNode();
        familymember.name = name;
        familymember.ancestor = this.current;
        if (this.current.children == null) {
            this.current.children = familymember;
               if(this.current.partner != null){
                    this.current = this.current.partner;
                    this.current.children = familymember;
        } else {
            FamilyTreeNode next = this.current.children;
            while (next.sibling != null) {
                next = next.sibling;
            }
            next.sibling = familymember;
        }
      }
    }
    
 //ability to add partner
    public void addPartner(String name) throws AlreadyGotAPartnerException {
        FamilyTreeNode familymember = new FamilyTreeNode();
        familymember.name = name;
        familymember.ancestor = this.current;
        this.current.partner = familymember;
        if((this.current.partner) == null){
            this.current.partner = familymember;
        }
            else
                throw new AlreadyGotAPartnerException();}    
    
//gets information on partner
    private String getPartner(FamilyTreeNode familymember) {
        String partnerDetails = new String();
        familymember = familymember.partner;
        if (familymember == null) {
            partnerDetails += "    has no partner\n";
        } else {
            partnerDetails += " partner " + familymember.name + "\n";
        }
        return partnerDetails;
    }


 //finds family member   
    public void findFamilyMember(String name) throws FamilyMemberNotFoundException{
        FamilyTreeNode familymember;
        FamilyTreeNode children;
        if (name.equalsIgnoreCase(this.ancestor.name)) {
            familymember = this.ancestor;
        } else {
            if(this.ancestor.children==null)
                throw new FamilyMemberNotFoundException();
            familymember = this.checkChildren(name, this.ancestor);
            if (familymember == null) {
                children = this.ancestor.children;
                while (familymember==null) {
                    familymember = this.checkChildren(name, children);
                    if (familymember == null) {
                        children = children.sibling;
                        if (children == null) {
                            throw new FamilyMemberNotFoundException();
                        }
                    }
                }
            }
        }
        this.current=familymember;
        getCurrent();
    }

    
    private FamilyTreeNode checkChildren(String name, FamilyTreeNode familymember) {
        familymember=familymember.children;
        Boolean searching=familymember!=null;
        while (searching) {
            if (name.equalsIgnoreCase(familymember.name)) {
                searching = false;
            } else {
                familymember = familymember.sibling;
                if (familymember == null) {
                    searching = false;
                }
            }
        }
        return familymember;
    }

//so that currently selected family member can be displayed
    public String getCurrent() {
        String currentDetails = new String();
        currentDetails += this.current.name +getPartner(this.current);
        if (this.current.ancestor != null) {
            currentDetails += " is the child of " + this.current.ancestor.name + '\n';
        } else {
            currentDetails += " is the ancestor\n";
        }
        currentDetails += this.getChildren(this.current);
        return currentDetails;
    }

}

