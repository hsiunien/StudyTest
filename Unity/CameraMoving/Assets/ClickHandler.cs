using System.Collections;
using System.Collections.Generic;
using System.Reflection;
using Cinemachine;
using DG.Tweening;
using Unity.VisualScripting;
using UnityEngine;
using UnityEngine.Serialization;
using UnityEngine.UI;

public class ClickHandler : MonoBehaviour
{
    // Event delegates triggered on click.
    [FormerlySerializedAs("onClick")]
    [SerializeField]
    private Button.ButtonClickedEvent m_OnClick = new Button.ButtonClickedEvent();

    public CinemachineVirtualCamera VirtualCamera; 
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
                m_OnClick.Invoke();
            }
        }
 
    }

    public void ChangeFieldOfView()
    {
        Debug.Log($"clicked,{Camera.main.depth}, {Camera.main.fieldOfView}");
        DOTween.To(() => Camera.main.fieldOfView, x => Camera.main.fieldOfView = x, 20f, 1);
    }

    public void OnChangeCamera()
    {
        VirtualCamera.gameObject.SetActive(!VirtualCamera.gameObject.activeSelf);
    }
}