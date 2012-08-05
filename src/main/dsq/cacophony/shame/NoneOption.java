package dsq.cacophony.shame;

public class NoneOption<T> implements Option<T> {
    public <A> A fold(final G<T, A> set, final F<A> notSet) {
        return notSet.f();
    }

    public Option<T> setIfUnset(final T value) {
        return new SomeOption<T>(value);
    }

    public T getOr(final T value) {
        return value;
    }

    public Option<T> or(final Option<T> value) {
        return value;
    }

    public <B> Option<B> bind(final G<T, Option<B>> g) {
        return new NoneOption<B>();
    }
}
