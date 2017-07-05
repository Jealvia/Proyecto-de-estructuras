/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.TDAs;

/**
 *
 * @author User
 */
public interface List<E> {
        public boolean isEmpty();
        public E get(int i);
        public boolean add(E element, int i);
        public List<E> concat(List<E> list);
        public boolean addFirst(E element);
        public boolean addLast(E element);
        public boolean remove(int index);
        public boolean removeElement(E element);
        public boolean cointains(E element);
        public boolean removeFirst();
        public boolean removeLast();
        public int size();
        public List<E> slicing(int inicio, int numFinal);
}
