
public class FamilyTree {
       public class FamilyMemberNotFoundException extends Exception{}

    private class FamilyNode {
        private String name;
        private FamilyNode ancestor;
        private FamilyNode sibling;
        private FamilyNode children;
        private FamilyNode partner;
    }

    private FamilyNode ancestor;
    private FamilyNode current;

    public FamilyTree(String ancestorName) {
        this.ancestor = new FamilyNode();
        this.ancestor.name = ancestorName;
        this.current = this.ancestor;
    }

 //using toString allows a string to be returned that shows the specified inputs 
    public String toString() {
        String familyDetails = new String();
        familyDetails += this.ancestor.name + "\n";
        FamilyNode familymember = this.ancestor.children;
        if (familymember == null) {
            familyDetails += "  has no children\n";
        } else {
            while (familymember != null) {
                familyDetails += "  " + familymember.name + "\n";
                familyDetails += this.getChildren(familymember);
                familyDetails += this.getPartner(familymember);
                familymember = familymember.sibling;
            }
        }
        return familyDetails;
    }

    private String getChildren(FamilyNode familymember) {
        String childrenDetails = new String();
        familymember = familymember.children;
        if (familymember == null) {
            childrenDetails += "    has no children\n";
        } else {
            while (familymember != null) {
                childrenDetails += "    " + familymember.name + "\n";
                familymember = familymember.sibling;
            }
        }
        return childrenDetails;
    }
    
    private String getPartner(FamilyNode familymember) {
        String partnerDetails = new String();
        familymember = familymember.partner;
        if (familymember == null) {
            partnerDetails += "    has no partner\n"; }
        else {
            partnerDetails += " partner " + familymember.name + "\n" ; }
        
        return partnerDetails;
        
           
    }

    public void addChild(String name) {
        FamilyNode familymember = new FamilyNode();
        familymember.name = name;
        familymember.ancestor = this.current;
        if (this.current.children == null) {
            this.current.children = familymember;
        } else {
            FamilyNode next = this.current.children;
            while (next.sibling != null) {
                next = next.sibling;
            }
            next.sibling = familymember;
        }
    }
    
    public void addPartner(String name) {
        FamilyNode familymember = new FamilyNode();
        familymember.name = name;
        familymember.ancestor = this.current;
        if (this.current.children == null) {
            this.current.children = familymember;
        } else {
            FamilyNode next = this.current.partner;
            while (next.partner != null) {
                next = next.partner;
            }
            next.partner = familymember;
        }
    }

    public void findFamilyMember(String name) throws FamilyMemberNotFoundException{
        FamilyNode familymember;
        FamilyNode children;
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
    }

    private FamilyNode checkChildren(String name, FamilyNode familymember) {
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

    public String getCurrent() {
        String currentDetails = new String();
        currentDetails += this.current.name;
        if (this.current.ancestor != null) {
            currentDetails += " is the child of " + this.current.ancestor.name + '\n';
        } else {
            currentDetails += " is the ancestor\n";
        }
        currentDetails += this.getChildren(this.current);
        return currentDetails;
    }

}

