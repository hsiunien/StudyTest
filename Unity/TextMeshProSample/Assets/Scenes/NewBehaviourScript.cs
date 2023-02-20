using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;

public class NewBehaviourScript : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {
        // var inf=GetComponent<TMP_InputField>();
        // inf.onFocusSelectAll = true; 
    }

    public void onTextChanged()
    {
  
        
    }
    IEnumerator DelayedAction()
    {
        yield return null;
        var inf=GetComponent<TMP_InputField>();
        inf.caretPosition = inf.textComponent.textInfo.characterCount;
    }
    
}
