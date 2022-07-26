package frc.LibBlue.StateMachine;

public abstract class State {
    /**
     * This class is a state descriptor, 
     * nor the StateMachineSchedular or the StateMachineManager are using this class.
     * This class is only for the user's connvinence.
     * It let the user easly identify states in the 'isTransitionable()' method.
     */
    class StateDescriptor {
        private String name;
        private int id;
        private int[] parentsId;

        public StateDescriptor(String name) {
            this.name = name;
            this.id = 0; // default value.
            this.parentsId = new int[0]; // default value, an empty array.
        }

        public StateDescriptor(String name, int id) {
            this(name);

            this.id = id;
        }

        public StateDescriptor(String name, int id, int[] parentsId) {
            this(name, id);

            this.parentsId = parentsId;
        }

        public String getName() {
            return this.name;
        }

        public int getId() {
            return this.id;
        }

        public int[] getParentsId() {
            return this.parentsId;
        }
    }

    private StateDescriptor stateDescriptor;

    public State(StateDescriptor stateDescriptor) {
        this.stateDescriptor = stateDescriptor;
    }

    public StateDescriptor getStateDescriptor() {
        return this.stateDescriptor;
    }

    public abstract void initialize();

    public abstract void run();

    public abstract void end();

    public abstract boolean isTransitionable(StateDescriptor stateDescriptor);
}
