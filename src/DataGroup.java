public class DataGroup {
    GroupCriterion criterion;
    public DataGroup(GroupCriterion criterion){
        this.criterion = criterion;
    }
     public int getKey(Student s){
        return criterion.getGroupKey(s);
     }

}
