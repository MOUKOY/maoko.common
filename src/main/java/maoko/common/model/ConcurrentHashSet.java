package maoko.common.model;

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashSet<E> extends AbstractSet<E> implements Set<E>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2561620184773360931L;

	private static final Object PRESENT = new Object();
	private final ConcurrentHashMap<E, Object> map;

	public ConcurrentHashSet() {
		map = new ConcurrentHashMap<E, Object>();
	}

	public ConcurrentHashSet(int initCapacity) {
		map = new ConcurrentHashMap<E, Object>(initCapacity);
	}

	@Override
	public Iterator<E> iterator() {
		return map.keySet().iterator();
	}

	@Override
	public int size() {
		return map.size();

	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public boolean contains(Object t) {
		return map.containsKey(t);
	}

	public boolean add(E e) {
		return map.put(e, PRESENT) == null;
	}

	public boolean remove(Object o) {
		return map.remove(o) == PRESENT;
	}

	public void clear() {
		map.clear();
	}

	public List<E> getValues() {
		List<E> keys = new ArrayList<E>();
		Set<E> set = map.keySet();
		for (Iterator<E> it = set.iterator(); it.hasNext();) {
			E e = it.next();
			keys.add(e);
		}
		return keys;
	}
}
