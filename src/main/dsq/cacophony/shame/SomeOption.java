package dsq.cacophony.shame;

public class SomeOption<T> implements Option<T> {
    
    private final T value;

    public SomeOption(final T value) {
        this.value = value;
    }

    public <A> A fold(final G<T, A> set, final F<A> notSet) {
        return set.g(value);
    }

    // FIX 4/03/12 Is this right? Should I have this ... or am I breaking some principle of something or other?
    public Option<T> setIfUnset(final T value) {
        return new SomeOption<T>(this.value);
    }

    public T getOr(final T value) {
        return this.value;
    }

    public Option<T> or(final Option<T> value) {
        return this;
    }

    public <B> Option<B> bind(final G<T, Option<B>> g) {
        return g.g(this.value);
    }
}
