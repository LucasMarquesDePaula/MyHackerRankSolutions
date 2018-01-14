package interview.amazon;


import java.util.ArrayList;
import java.util.List;

public class Drones {

    private static class Drone {

        private int id;
        private int flightRange;

        public Drone(int id, int flightRange) {

            this.id = id;
            this.flightRange = flightRange;
        }

        public int getId() {
            return id;
        }

        public int getFlightRange() {
            return flightRange;
        }
    }

    private static class DroneNode {

        private final Drone drone;
        private DroneNode prev;
        private DroneNode next;

        public DroneNode(Drone drone) {
            this.drone = drone;
        }

        /**
         * @return the drone
         */
        public Drone getDrone() {
            return drone;
        }

        /**
         * @return the previous node
         */
        public DroneNode getPrev() {
            return prev;
        }

        /**
         * @param prev the previous node to set
         */
        public void setPrev(DroneNode prev) {
            this.prev = prev;
        }

        /**
         * @return the next node
         */
        public DroneNode getNext() {
            return next;
        }

        /**
         * @param next the next to set
         */
        public void setNext(DroneNode next) {
            this.next = next;
        }
    }

    private static class DroneList {

        private final int maxSize;
        private int size;
        private DroneNode first;
        private DroneNode last;

        public DroneList(int maxSize) {
            this.maxSize = maxSize;
        }

        /**
         * @return the maxSize
         */
        public int getMaxSize() {
            return maxSize;
        }

        /**
         * @return the size
         */
        public int getSize() {
            return size;
        }

        /**
         * Add a drone on list sorting by its flight range
         *
         * @param node the node to be added
         */
        public void add(Drone drone) {
            this.add(new DroneNode(drone));
        }

        /**
         * Add a node on list sorting by its flight range
         *
         * @param node the node to be added
         */
        public void add(DroneNode node) {

            // Add drone 
            // If list is empty
            if (this.getSize() == 0) {
                this.add(node, node, node);
                return;
            }

            try {
                // Find node's position
                DroneNode position = findPosition(node);
                this.add(node, position.getPrev(), position);

                if (this.first.getDrone().getFlightRange() < node.getDrone().getFlightRange()) {
                    this.first = node;
                }

                if (this.last.getDrone().getFlightRange() > node.getDrone().getFlightRange()) {
                    this.last = node;
                }
            } catch (PositionNotFoudException ignored) {

            }
        }

        /**
         * Add a node on list
         *
         * @param node the node to be added
         * @param prev the previous node
         * @param next the next node
         */
        public void add(DroneNode node, DroneNode prev, DroneNode next) {
            node.setPrev(prev);
            node.setNext(next);
            prev.setNext(node);
            next.setPrev(node);

            this.added(node);
        }

        /**
         * Triggered when some drone is added to list
         */
        private void added(DroneNode node) {
            this.size++;

            // If list was empty set its last and first node as the node added
            if (this.getSize() == 1) {
                this.first = node;
                this.last = node;
            }

            // Remove the last drone if list size exceeded its max size
            if (this.getSize() > this.getMaxSize()) {
                this.remove(this.last);
            }
        }

        /**
         * Remove a node from list
         *
         * @param node the node to be removed
         */
        private void remove(DroneNode node) {
            if (this.last == node) {
                this.last = node.getPrev();
            }

            if (this.first == node) {
                this.first = node.getNext();
            }
            // Remove node from list
            node.getPrev().setNext(node.getNext());
            node.getNext().setPrev(node.getPrev());

            node.setPrev(null);
            node.setNext(null);

            this.removed();
        }

        /**
         * Triggered when some drone is removed from list
         */
        private void removed() {
            this.size--;

            // Set null value on pointers if its empty
            if (this.getSize() == 0) {
                this.first = null;
                this.last = null;
            }
        }

        /**
         * Find node's position by its flight range
         *
         * @param node the node to have its position found
         * @throws PositionNotFoudException if position was not found
         */
        public DroneNode findPosition(DroneNode node) throws PositionNotFoudException {
            Drone drone = node.getDrone();

            // If node's flight range is smallest then the last-node's flight range 
            if (this.last.getDrone().getFlightRange() > node.getDrone().getFlightRange()) {
                // if list is full so it won't be added
                if (this.getMaxSize() == this.getSize()) {
                    throw new PositionNotFoudException();
                }

                return this.first;
            }

            // Find the position
            DroneNode position = this.first;
            do {
                if (position.getDrone().getFlightRange() <= drone.getFlightRange()) {
                    return position;
                }
                position = position.getNext();
            } while (position != first);

            throw new PositionNotFoudException();
        }

        /**
         * Return Drones' ids sorted by its flight range
         */
        public List<Integer> toList() {
            List<Integer> list = new ArrayList<>(this.getSize());

            if (this.getSize() == 0) {
                return list;
            }

            DroneNode current = this.first;

            do {
                list.add(current.getDrone().getId());
                current = current.getNext();
            } while (current != this.first);

            return list;
        }

        private class PositionNotFoudException extends Exception {

        }

    }

    static List<Integer> greatestFlightRangeDrones(Integer numberOfRequiredDrones, List<Drone> drones, List<Integer> inMaintenanceDrones) {

        // Pass through drones list getting the n drones with greatest range
        DroneList greatestRange = new DroneList(numberOfRequiredDrones);

        for (Drone drone : drones) {
            // If drone is not in mainteinance
            if (!inMaintenanceDrones.contains(drone.getId())) {
                greatestRange.add(drone);
            }
        }

        return greatestRange.toList();
    }

    public static void main(String[] args) {
        int numberOfRequiredDrones = 3;
        List<Drone> drones = new ArrayList<Drone>() {
            {
                add(new Drone(4, 11));
                add(new Drone(2, 15));
                add(new Drone(5, 16));
                add(new Drone(3, 40));
                add(new Drone(1, 20));
                add(new Drone(9, 60));
                add(new Drone(6, 22));
            }
        };

        List<Integer> inMaintenanceDrones = new ArrayList<Integer>() {
            {
                add(1);
                add(5);
                add(9);
            }
        };

        List<Integer> ids = greatestFlightRangeDrones(numberOfRequiredDrones, drones, inMaintenanceDrones);
        for (Integer id : ids) {
            System.out.println(id);
        }
    }
}
