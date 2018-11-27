package eu.gotoinc.requesinjava_mvvm.util.architecture;

import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;
import androidx.lifecycle.ViewModel;


public class ObservableViewModel extends ViewModel implements Observable {
	private PropertyChangeRegistry callbacks = new PropertyChangeRegistry();

	@Override
	public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
		callbacks.add(callback);
	}

	@Override
	public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
		callbacks.remove(callback);
	}

	/**
	 * Notifies observers that all properties of this instance have changed.
	 */
	protected void notifyChange() {
		callbacks.notifyCallbacks(this, 0, null);
	}

	/**
	 * Notifies observers that a specific property has changed. The getter for the
	 * property that changes should be marked with the @Bindable annotation to
	 * generate a field in the BR class to be used as the fieldId parameter.
	 *
	 * @param fieldId The generated BR id for the Bindable field.
	 */
	protected void notifyPropertyChanged(int fieldId) {
		callbacks.notifyCallbacks(this, fieldId, null);
	}
}