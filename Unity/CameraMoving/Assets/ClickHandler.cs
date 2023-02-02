using System.Collections;
using System.Collections.Generic;
using DG.Tweening;
using Unity.VisualScripting;
using UnityEngine;

public class ClickHandler : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {
        DOTween.Init();
    }

    // Update is called once per frame
    void Update()
    { 
            if (Input.GetMouseButtonDown(0))
            {
               
            }
            
            if(Input.GetMouseButtonUp(0))
            {
    
                castRay(Input.mousePosition);
            }
 
    }
    private void castRay(Vector3 position)
    {
        Ray ray = Camera.main.ScreenPointToRay(position);
        RaycastHit hit;
        if (Physics.Raycast(ray, out hit))
        {
            GameObject hitObject = hit.collider.gameObject;
            if (hitObject == this.gameObject)
            {
                OnClicked();
            }
        }
 
    }

    private void OnClicked()
    {
        Debug.Log($"clicked,{Camera.main.depth}, {Camera.main.fieldOfView}");
        DOTween.To(() => Camera.main.fieldOfView, x => Camera.main.fieldOfView = x, 20f, 1);
    }
}