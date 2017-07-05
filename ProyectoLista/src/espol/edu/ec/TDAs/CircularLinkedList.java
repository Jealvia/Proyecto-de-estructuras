/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.TDAs;

/**
 *
 * @author User
 * @param <E>
 */
public class CircularLinkedList<E> implements List<E> {
    
    private Nodo<E> first, last, actual;
    private int efectivo, llamados;
    
    public CircularLinkedList()
    {
        this.first=this.last=null;
        this.efectivo=this.llamados=0;
    }

    @Override
    public boolean isEmpty() {
        return (this.first == null && this.last == null);    
    }

    @Override
    public E get(int index) {
        if (!this.isEmpty())
        {
            Nodo<E> temporal = this.first;
            for (int i=0; i<=index && index<efectivo; i++)
            {
                if (i==index)
                {
                    return temporal.getData();
                }
                temporal = temporal.getNext();
            }
        }
        return null;
    }

    @Override
    public boolean add(E element, int i) {
        if (i<(this.size()-1) && element != null && i>=0)
        {
            if (i == 0)
            {
                this.addFirst(element);
                return true;
            }
            else if (i == (this.size()-1))
            {
                this.addLast(element);
                return true;
            }
            else
            {
                Nodo<E> nuevoNodoAnterior = this.getPrevious(this.getNodo(i));
                Nodo<E> nuevoNodoSiguiente = this.getNodo(i);
                Nodo<E> nodoDeElement = new Nodo<>(element);
                nuevoNodoAnterior.setNext(nodoDeElement);
                nodoDeElement.setNext(nuevoNodoSiguiente);
                efectivo++;
                return true;
            }
        }
        return false;
    }

    @Override
    public List<E> concat(List<E> list) {
        List <E> nuevaLista = new CircularLinkedList<>();
        if (!list.isEmpty())
        {
            Nodo<E> temporal = this.first;
            do
            {
                nuevaLista.addLast(temporal.getData());
                temporal = temporal.getNext();
            }while (temporal != this.first);
            
            int i = 0;
            do
            {
                nuevaLista.addLast(list.get(i));
                i++;
            }while(i<=list.size()-1);
        }
        return nuevaLista;
    }

    @Override
    public boolean addFirst(E element) {
        Nodo<E> nuevo = new Nodo<>(element);
        if (element == null)
        {
            return false;
        }
        else if (isEmpty())
        {
            this.first = nuevo;
            this.last = nuevo;
            this.last.setNext(this.first);
        }
        else
        {
            nuevo.setNext(this.first);
            this.first= nuevo;
            this.last.setNext(this.first);
        }
        efectivo++;
        return true;
    }

    @Override
    public boolean addLast(E element) {
        Nodo<E> nuevoNodo = new Nodo<>(element);
        if (element == null)
        {
            return false;
        }
        else if ( this.isEmpty())
        {
            this.first=this.last=nuevoNodo;
            this.last.setNext(this.first);
        }
        else
        {
            this.last.setNext(nuevoNodo);
            this.last = nuevoNodo;
            this.last.setNext(this.first);
        }
        efectivo++;
        return true;
    }

    @Override
    public boolean remove(int index) {
        if (index<(this.size()-1) && index>=0)
        {
            Nodo<E> temporal;
            if (index == 0)
            {
                return this.removeFirst();
            }
            else if (index == (this.size()-1))
            {
                return this.removeLast();
            }
            else
            {
                Nodo<E> nodoAnterior = this.getPrevious(this.getNodo(index));
                Nodo<E> nodoSiguiente = this.getNodo(index+1);
                Nodo<E> nodoARemover = this.getNodo(index);
                nodoAnterior.setNext(nodoSiguiente);
                nodoARemover.setNext(null);
                efectivo--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeElement(E element) {
        if(!this.isEmpty())
        {
            Nodo<E> temporal;
            if (this.first==this.last && this.first.getData().equals(element))
            {
                this.first = this.last = null;
                efectivo--;
                return true;
            }
            else if(this.first.getData().equals(element))
            {
                return this.removeFirst();
            }
            else
            {
                Nodo<E> i = this.first.getNext();
                do
                {
                    if (i.getData().equals(element))
                    {
                        if (this.last == i)
                        {
                            return this.removeLast();
                        }
                        else
                        {
                            temporal = this.getPrevious(i);
                            temporal.setNext(i.getNext());
                            i.setNext(null);
                            efectivo--;
                            return true;
                        }
                    }
                    i = i.getNext();
                } while(i != this.first);
            }
        }
        return false;
    }

    @Override
    public boolean cointains(E element) {
        Nodo<E> temporal =this.first;
        do
        {
            if (temporal.getData().equals(element))
            {
                return true;
            }
            temporal = temporal.getNext();
        } while(temporal != this.first);
        return false;
    }

    @Override
    public boolean removeFirst() {
        if(this.isEmpty())
        {
            return false;
        }
        else if (this.first == this.last)
        {
            this.first = this.last = null;
        }
        else
        {
            Nodo<E> temporal = this.first.getNext();
            this.first.setNext(null);
            this.first = temporal;
            this.last.setNext(this.first);
        }
        efectivo--;
        return true;
    }

    @Override
    public boolean removeLast() {
        if (this.isEmpty())
        {
            return false;
        }
        else if (this.first == this.last)
        {
            this.first = this.last = null;
        }
        else
        {
            Nodo<E> previo = this.getPrevious(this.last);
            previo.setNext(null);
            this.last = previo;
            this.last.setNext(this.first);
        }
        efectivo--;
        return true;
    }

    @Override
    public int size() {
        return efectivo;
    }

    @Override
    public List<E> slicing(int inicio, int numFinal) {
        List<E> nuevaLista = new CircularLinkedList<>();
        if (inicio>=0 && inicio<numFinal && numFinal<this.size() && !this.isEmpty())
        {
            for (int i = inicio; i<numFinal; i++)
            {
                nuevaLista.addLast(this.get(i));
            }
        }
        return nuevaLista;
    }
    
    @Override
    public String toString()
    {
        if(!this.isEmpty()){
            String listaAString = "[";
            Nodo<E> temporal = this.first;
            do
            {
                listaAString += temporal.getData()+",";
                temporal = temporal.getNext();

            } while(temporal != this.first);

            listaAString = listaAString.substring(0,listaAString.length()-1)+"]";
            return listaAString;
        }
        else{return "[]";}
    }
    
    private Nodo<E> getPrevious(Nodo<E> nodo)
    {
        if(nodo == this.first)
        {
            return this.last;
        }
        else{
            Nodo<E> temporal = this.first.getNext();
            do
            {
                if (temporal.getNext()== nodo)
                {
                    return temporal;
                }
                temporal = temporal.getNext();
            } while(nodo!= this.first);
        }
        return null;
    }
    
    private Nodo<E> getNodo(int index)
    {
        if(index<(this.size()-1) && !this.isEmpty() && index>=0)
        {
            if (index == 0)
            {
                return this.first;
            }
            else if (index == (this.size()-1))
            {
                return this.last;
            }
            else
            {
                Nodo<E> temporal = this.first.getNext();
                for(int i = 1; i<this.size()-1; i++)
                {
                    if(i == index)
                    {
                        return temporal;
                    }
                    temporal = temporal.getNext();
                }
            }
        }
        return null;
    }

    public E next()
    {
        if(this.llamados==0)
        {
            this.actual = this.first.getNext();
            this.llamados += 1;
            return this.first.getData();
        }
        else
        {
            E temp = this.actual.getData();
            this.actual = this.actual.getNext();
            this.llamados += 1;
            return temp;
        }

    }

    
}
