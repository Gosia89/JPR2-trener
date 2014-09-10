package mod06._phantom01;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class GhostReference extends PhantomReference {
	private static final Set refs = new HashSet();
	private static final Field referent;

	static {
		try {
			referent = Reference.class.getDeclaredField("referent");
			referent.setAccessible(true);
		} catch (NoSuchFieldException e) {
			throw new RuntimeException("Field 'referent' not found");
		}
	}

	public GhostReference(Object referent, ReferenceQueue queue) {
		super(referent, queue);
		refs.add(this);
	}

	@Override
	public void clear() {
		refs.remove(this);
		super.clear();
	}

	public Object getReferent() {
		try {
			return referent.get(this);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException("referent should be accessible!");
		}
	}
}
